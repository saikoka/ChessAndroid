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

import java.util.Arrays;

import model.Bishop;
import model.Knight;
import model.Piece;
import model.Queen;
import model.Rook;

import static com.example.chessandroid72.BoardAdapter.board;
import static model.Chess.checkMate;
import static model.Chess.inCheck;
import static model.Chess.movePiece;

public class ReplayGame extends AppCompatActivity{

    public Piece[][] prevBoard=null;
    public BoardAdapter customAdapter;
    public BoardAdapter test;
    public int count = 5;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.replay_board);
        final GridView chessboard = (GridView) findViewById(R.id.chessboard);
        customAdapter = new BoardAdapter(this);
        test = customAdapter;
        Button next = findViewById(R.id.next);
        Button exit = findViewById(R.id.exit);

        chessboard.setAdapter(customAdapter);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReplayGame.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count<0){
                    //do something
                    Toast.makeText(getApplicationContext(),"game finished", Toast.LENGTH_SHORT).show();
                }else{

                    test.board[6][count] = null;
                    count --;
                    //setContentView(R.layout.replay_board);
                    //GridView chessboard = (GridView) findViewById(R.id.chessboard);
                    chessboard.setAdapter(test);
                }

            }
        });

    }


}
