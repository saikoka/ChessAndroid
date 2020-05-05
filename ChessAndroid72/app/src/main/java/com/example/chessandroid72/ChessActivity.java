package com.example.chessandroid72;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Bishop;
import model.Knight;
import model.Piece;
import model.Queen;
import model.Rook;

import static com.example.chessandroid72.BoardAdapter.board;
import static model.Chess.checkMate;
import static model.Chess.inCheck;
import static model.Chess.movePiece;

public class ChessActivity extends AppCompatActivity {
    public List <BoardAdapter> states = new ArrayList<>();
    public Piece[][] prevBoard=null;
    public BoardAdapter customAdapter;
    static int promotionx;
    static int promotiony;
    static boolean turn=false;
    public static String promotionString;
    int currRow;
    int row;
    int currCol;
    int col;
    boolean clicked;
    char promotionPiece;
    Piece temp1;
    char tempT1;
    boolean check;
    boolean tempC1;
    boolean tempSp1;
    int tempX1;
    int tempY1;
    Piece temp2;
    int tempX2;
    int tempY2;
    char tempT2;
    boolean tempC2;
    boolean tempSp2;
    private String[] promotionNames;
    boolean result;
    boolean newCheck;
    CharSequence checkText= "check";
    CharSequence whiteText= "White's Move";
    CharSequence blackText= "Black's Move";
    CharSequence error= "Illegal move, try again";
    CharSequence emptyPrev= "No past move available.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView chessboard = (GridView) findViewById(R.id.chessboard);
        customAdapter = new BoardAdapter(this);
        Button undo = findViewById(R.id.undo);
        Button resign = findViewById(R.id.resign);
        Button draw = findViewById(R.id.draw);
        Button ai = findViewById(R.id.ai);
        chessboard.setAdapter(customAdapter);
        ai.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                for(int i=0; i<8;i++){
                    for(int j=0; j<8;j++){
                        if(board[i][j]!=null) {
                            if (board[i][j].type == 'p' && board[i][j].color == turn) {
                                if (turn == false) {//white
                                    if ((i - 1) >= 0) {
                                        if (board[i - 1][j] == null) {
                                            prevBoard=deepCopy(board);
                                            board[i - 1][j] = board[i][j];
                                            board[i - 1][j].x = i - 1;
                                            board[i][j] = null;
                                            turn = !turn;
                                            customAdapter.notifyDataSetChanged();
                                            return;
                                        }
                                    }
                                }
                                else {

                                    if ((i + 1) <= 7) {

                                        if (board[i + 1][j] == null) {
                                            prevBoard=deepCopy(board);
                                            board[i + 1][j] = board[i][j];
                                            board[i + 1][j].x = i + 1;
                                            board[i][j] = null;
                                            turn = !turn;
                                            customAdapter.notifyDataSetChanged();
                                            return;
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        });
        draw.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        resign.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        undo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(prevBoard==null){
                    Toast.makeText(getApplicationContext(),emptyPrev, Toast.LENGTH_SHORT).show();
                }
                else{
                    board=prevBoard;
                    turn=!turn;
                    customAdapter.notifyDataSetChanged();
                }
            }
        });
        chessboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                row = position/8;
                col = position%8;

                if(clicked){
                    //Log.i("position", "position x is :"+row+"  pos y is :"+col);
                    //Log.i("position", "position x2 is :"+currRow+"  pos y2 is :"+currCol);
                    prevBoard=deepCopy(board);
                    promotionPiece= ' ';
                    temp1 = board[currRow][currCol];
                    tempT1=board[currRow][currCol].type;
                    tempC1=board[currRow][currCol].color;
                    tempSp1=board[currRow][currCol].special;
                    tempX1=board[currRow][currCol].x;
                    tempY1=board[currRow][currCol].y;

                    temp2 = board[row][col];
                    tempX2=0;
                    tempY2=0;
                    tempT2=0;
                    tempC2=false;
                    tempSp2=false;
                    if (board[row][col]!=null){
                        tempX2=board[row][col].x;
                        tempY2=board[row][col].y;
                        tempT2=board[row][col].type;
                        tempC2=board[row][col].color;
                        tempSp2=board[row][col].special;

                    }

                    result = movePiece(currRow,currCol,row,col,board,turn);
                    if(result){
                        for(int i=0;i<7;i++){
                            if(board[0][i]!=null) {
                                if (board[0][i].getPiece().equals("wp")) {
                                    promotionx = 0;
                                    promotiony = i;
                                    Intent intent = new Intent(ChessActivity.this, PromotionActivity.class);
                                    startActivityForResult(intent, 1);
                                    return;
                                    //getPromotion(promotionString);

                                }
                            }
                            if(board[7][i]!=null) {
                                if (board[7][i].getPiece().equals("bp")) {
                                    promotionx = 7;
                                    promotiony = i;
                                    Intent intent = new Intent(ChessActivity.this, PromotionActivity.class);
                                    startActivityForResult(intent, 1);
                                    return;
                                    //Toast.makeText(getApplicationContext(), realError, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                    if(check) {//already in check

                        newCheck = inCheck(board, turn);
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
                        newCheck = inCheck(board, turn);
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

                        //Log.i("XD", String.valueOf(turn));
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
    public static Piece[][] deepCopy(Piece[][] original) {
        if (original == null) {
            return null;
        }

        final Piece[][] result = new Piece[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                promotionString = data.getStringExtra("piece");
                getPromotion(promotionString);
                if(check) {//already in check

                    newCheck = inCheck(board, turn);
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
                    newCheck = inCheck(board, turn);
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

                    //Log.i("XD", String.valueOf(turn));
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
            states.add(customAdapter);

        }
    }

    /*public void openDialog(){
        PromotionDialog promotionDialog = new PromotionDialog();
        promotionDialog.show(getSupportFragmentManager(),"Promotion Dialog");
    }*/

    public static void getPromotion(String piece){
        switch(piece){
            case "Queen":
                board[promotionx][promotiony]= new Queen(turn, promotionx, promotiony);

                break;
            case "Knight":
                board[promotionx][promotiony]= new Knight(turn, promotionx, promotiony);

                break;
            case "Bishop":
                board[promotionx][promotiony]= new Bishop(turn, promotionx, promotiony);

                break;
            case "Rook":
                board[promotionx][promotiony]= new Rook(!turn, promotionx, promotiony);

                break;
        }


    }

    /*@Override
    public void getString(String pieceName) {
        switch(pieceName){
            case "Queen":
                board[promotionx][promotiony]= new Queen(turn, promotionx, promotiony);

                break;
            case "Knight":
                board[promotionx][promotiony]= new Knight(turn, promotionx, promotiony);

                break;
            case "Bishop":
                board[promotionx][promotiony]= new Bishop(turn, promotionx, promotiony);

                break;
            case "Rook":
                board[promotionx][promotiony]= new Rook(turn, promotionx, promotiony);

                break;
        }
    }*/
}
