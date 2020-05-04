package com.example.chessandroid72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PostgameActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postgame);
        Button saveGame = findViewById(R.id.save);
        Button exitGame = findViewById(R.id.exit);

        //TextView winner = findViewById(R.id.wincolor);

        //winner.setText("POG");

        saveGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        exitGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostgameActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });


    }
}
