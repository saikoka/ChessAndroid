package com.example.chessandroid72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {
    public static final String type = null;
    public BoardAdapter customAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        customAdapter = new BoardAdapter(this);
        customAdapter.cleanBoard();

        setContentView(R.layout.main);
        Button newGame=findViewById(R.id.new_game);
        Button gameList= findViewById(R.id.game_list);
        Button gameList2= findViewById(R.id.game_list2);


        newGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, ChessActivity.class);
                startActivity(intent);
                finish();
            }
        });

        gameList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(type, "name");
                Intent intent = new Intent(MainPageActivity.this, SavedPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        gameList2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(type, "date");
                Intent intent = new Intent(MainPageActivity.this, SavedPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
