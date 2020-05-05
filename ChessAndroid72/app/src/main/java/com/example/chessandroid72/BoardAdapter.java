package com.example.chessandroid72;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import model.Bishop;
import model.King;
import model.Knight;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;


public class BoardAdapter extends BaseAdapter {
    private Context context;
    public static Piece[][] board= new Piece[8][8];

    public BoardAdapter(Context context){
        this.context=context;
        board[0][0]= new Rook(true, 0,0);
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
        board[6][7]= new Pawn(false,6,7);
    }
    public void cleanBoard(){
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                board[i][j]=null;
            }
        }
    }
    @Override
    public int getCount() {
        return 64;
    }

    @Override
    public Object getItem(int position) {
        return board[position/8][position%8];
    }

    public Object getItem(int row, int column){
        return board[row][column];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView==null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        }
        else{
            imageView = (ImageView) convertView;
        }

        int column= position % 8;
        int row = position/8;
        if(row%2==0){
            if(column%2==0){
                imageView.setBackgroundColor(Color.parseColor("#E8B792"));
            }
            else{
                imageView.setBackgroundColor(Color.parseColor("#806257"));
            }
        }
        else{
            if(column%2==0){
                imageView.setBackgroundColor(Color.parseColor("#806257"));
            }
            else{
                imageView.setBackgroundColor(Color.parseColor("#E8B792"));
            }
        }
        if(board[row][column]==null){
            imageView.setImageDrawable(null);
            return imageView;
        }
        else{
            switch(board[row][column].getPiece()){
                case "bB":
                    imageView.setImageResource(R.drawable.blackbishop);
                    break;
                case "wB" :
                    imageView.setImageResource(R.drawable.whitebishop);
                    break;
                case "bK":
                    imageView.setImageResource(R.drawable.blackking);
                    break;
                case "wK" :
                    imageView.setImageResource(R.drawable.whiteking);
                    break;
                case "bN":
                    imageView.setImageResource(R.drawable.blackknight);
                    break;
                case "wN" :
                    imageView.setImageResource(R.drawable.whiteknight);
                    break;
                case "bQ":
                    imageView.setImageResource(R.drawable.blackqueen);
                    break;
                case "wQ" :
                    imageView.setImageResource(R.drawable.whitequeen);
                    break;
                case "bR":
                    imageView.setImageResource(R.drawable.blackrook);
                    break;
                case "wR" :
                    imageView.setImageResource(R.drawable.whiterook);
                    break;
                case "bp":
                    imageView.setImageResource(R.drawable.blackpawn);
                    break;
                case "wp" :
                    imageView.setImageResource(R.drawable.whitepawn);
                    break;
            }

        }
        return imageView;
    }
}
