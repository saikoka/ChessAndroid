package com.example.chessandroid72;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import model.Bishop;
import model.Chess;
import model.King;
import model.Knight;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;

import static com.example.chessandroid72.BoardAdapter.board;
import static model.Chess.checkMate;
import static model.Chess.inCheck;
import static model.Chess.movePiece;

public class MainActivity extends AppCompatActivity {
    //public static Piece[][] board = new Piece[8][8];
    private BoardAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*board[0][0]= new Rook(true, 0,0);
        board[0][1]= new Knight(true,0,1);
        board[0][2]= new Bishop(true,0,2);
        board[0][3]= new Queen(true,0,3);
        board[0][4]= new King(true,0,4);
        board[0][5]= new Bishop(true,0,5);
        board[0][6]= new Knight(true,0,6);
        board[0][7]= new Rook(true,0,7);
        board[1][0]= new Pawn(true,1,0);
        board[1][1]= new Pawn(true,1,1);
        board[1][2]= new Pawn(true,1,2);
        board[1][3]= new Pawn(true,1,3);
        board[1][4]= new Pawn(true,1,4);
        board[1][5]= new Pawn(true,1,5);
        board[1][6]= new Pawn(true,1,6);
        board[1][7]= new Pawn(true,1,7);

        board[7][0]= new Rook(false,7,0);
        board[7][1]= new Knight(false,7,1);
        board[7][2]= new Bishop(false,7,2);
        board[7][3]= new Queen(false,7,3);
        board[7][4]= new King(false,7,4);
        board[7][5]= new Bishop(false,7,5);
        board[7][6]= new Knight(false,7,6);
        board[7][7]= new Rook(false,7,7);
        board[6][0]= new Pawn(false,6,0);
        board[6][1]= new Pawn(false,6,1);
        board[6][2]= new Pawn(false,6,2);
        board[6][3]= new Pawn(false,6,3);
        board[6][4]= new Pawn(false,6,4);
        board[6][5]= new Pawn(false,6,5);
        board[6][6]= new Pawn(false,6,6);
        board[6][7]= new Pawn(false,6,7);*/

        //Log.i("XD", "XD WAS HERE!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView chessboard = (GridView) findViewById(R.id.chessboard);
        customAdapter = new BoardAdapter(this);
        chessboard.setAdapter(customAdapter);
        chessboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int currRow;
            int newRow;
            int currCol;
            int newCol;
            boolean clicked;
            boolean turn=false;
            boolean check=false;

            CharSequence checkText= "check";
            CharSequence whiteText= "White's Move";
            CharSequence blackText= "Black's Move";
            CharSequence error= "Illegal move, try again";
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int row = position/8;
                int col = position%8;
                if(clicked){
                    //Log.i("position", "position x is :"+row+"  pos y is :"+col);
                    //Log.i("position", "position x2 is :"+currRow+"  pos y2 is :"+currCol);
                    char promotionPiece= ' ';
                    Piece temp1 = board[currRow][currCol];
                    char tempT1=board[currRow][currCol].type;
                    boolean tempC1=board[currRow][currCol].color;
                    boolean tempSp1=board[currRow][currCol].special;
                    int tempX1=board[currRow][currCol].x;
                    int tempY1=board[currRow][currCol].y;

                    Piece temp2 = board[row][col];
                    int tempX2=0;
                    int tempY2=0;
                    char tempT2=0;
                    boolean tempC2=false;
                    boolean tempSp2=false;
                    if (board[row][col]!=null){
                        tempX2=board[row][col].x;
                        tempY2=board[row][col].y;
                        tempT2=board[row][col].type;
                        tempC2=board[row][col].color;
                        tempSp2=board[row][col].special;

                    }

                    boolean result = movePiece(currRow,currCol,row,col,board,turn);
                    if(check) {//already in check

                        boolean newCheck = inCheck(board, turn);
                        if (newCheck){//did not fix

                            //System.out.println("Illegal move, try again");

                            board[currRow][currCol]=temp1;
                            board[currRow][currCol].type=tempT1;
                            board[currRow][currCol].special=tempSp1;
                            board[currRow][currCol].color=tempC1;
                            board[currRow][currCol].x=tempX1;
                            board[currRow][currCol].y=tempY1;

                            board[row][col]=temp2;
                            if (temp2!=null){
                                board[row][col].x=tempX2;
                                board[row][col].y=tempY2;
                                board[row][col].type=tempT2;
                                board[row][col].special=tempSp2;
                                board[row][col].color=tempC2;

                            }
                            //System.out.println("SPECIAL BOARD PRINT");
                            //printBoard(board);//temp to check that move is undone
                            result=false;
                        }else{//fixed
                            //System.out.println("you moved out of check");

                            check=false;
                        }


                    }else{//wasnt in check
                        boolean newCheck = inCheck(board, turn);
                        if (newCheck){
                            //System.out.println("moved into check");
                            //System.out.println("Illegal move, try again");

                            board[currRow][currCol]=temp1;
                            board[currRow][currCol].type=tempT1;
                            board[currRow][currCol].special=tempSp1;
                            board[currRow][currCol].color=tempC1;
                            board[currRow][currCol].x=tempX1;
                            board[currRow][currCol].y=tempY1;

                            board[row][col]=temp2;
                            if (temp2!=null){
                                board[row][col].x=tempX2;
                                board[row][col].y=tempY2;
                                board[row][col].type=tempT2;
                                board[row][col].special=tempSp2;
                                board[row][col].color=tempC2;

                            }
                            result=false;
                            // System.out.println("SPECIAL BOARD PRINT");
                            //printBoard(board);//temp to check that move is undone
                        }
                        else{
                            //System.out.println("didnt put self in check");
                        }
                    }
                    /*board[row][col]=board[currRow][currCol];
                    board[row][col].x=row;
                    board[row][col].y=col;
                    board[currRow][currCol]=null;*/
                    if(!result){
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        turn=!turn;
                        check=inCheck(board,turn);
                        if (check){
                            if (checkMate(board, turn,row, col)){
                                if(!turn){
                                    //System.out.println("Black wins");
                                }else {
                                    //System.out.println("White Wins");
                                }

                            }
                        }
                    }
                    if(check) {
                        Toast.makeText(getApplicationContext(), checkText, Toast.LENGTH_SHORT).show();
                    }
                    if(!turn) {
                        Toast.makeText(getApplicationContext(), whiteText, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), blackText, Toast.LENGTH_SHORT).show();

                    }
                    clicked=false;
                }
                else{
                    currRow=row;
                    currCol=col;
                    clicked=true;
                }
                //Log.i("position", "position x is :"+row+"  pos y is :"+col);


                customAdapter.notifyDataSetChanged();
            }
        });

    }

}
