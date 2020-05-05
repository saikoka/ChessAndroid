package com.example.chessandroid72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.chessandroid72.GameSer.findGame;

public class PostgameActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postgame);

        final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        final Date date = new Date();

        Button saveGame = findViewById(R.id.save);
        Button exitGame = findViewById(R.id.exit);

        Intent intent = getIntent();
        String message = intent.getStringExtra(ChessActivity.color);

        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        saveGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GameSer temp = findGame("default");
                TextInputEditText text = findViewById(R.id.savename);
                String val = text.getText().toString();

                temp.name = val;
                temp.date = dateFormat.format(date);

                Intent intent = new Intent(PostgameActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exitGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                GameSer.gameSerList.remove(findGame("default"));
                Intent intent = new Intent(PostgameActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
