package com.example.chessandroid72;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import model.Piece;


public class BoardAdapter extends BaseAdapter {
    private Context context;
    Piece[][] currBoard= new Piece[8][8];

    public BoardAdapter(Context context, Piece[][] newBoard){
        this.context=context;
        for (int i = 0; i < currBoard.length; i++)
        {
            for (int j = 0; j < currBoard[i].length; j++)
            {
                currBoard[i][j] = newBoard[i][j];

            }
        }
    }
    @Override
    public int getCount() {
        return 64;
    }

    @Override
    public Object getItem(int position) {
        return currBoard[position/8][position%8];
    }

    public Object getItem(int row, int column){
        return currBoard[row][column];
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
        if(currBoard[row][column]==null){
            return imageView;
        }
        else{
            switch(currBoard[row][column].getPiece()){
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
