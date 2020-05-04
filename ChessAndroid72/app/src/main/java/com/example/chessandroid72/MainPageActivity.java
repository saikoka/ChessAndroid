package com.example.chessandroid72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button newGame=findViewById(R.id.new_game);
        Button gameList= findViewById(R.id.game_list);

        newGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, ChessActivity.class);
                startActivity(intent);
            }
        });

        gameList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }
}
