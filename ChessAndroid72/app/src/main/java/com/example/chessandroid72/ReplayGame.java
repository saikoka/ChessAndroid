package com.example.chessandroid72;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import static com.example.chessandroid72.GameSer.findGame;
import static com.example.chessandroid72.BoardAdapter.board;
import static com.example.chessandroid72.GameSer.findGame2;


import model.Piece;


public class ReplayGame extends AppCompatActivity{

    public BoardAdapter customAdapter;
    int moves = 0;
    int count =0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String message = intent.getStringExtra(SavedPage.choice);
        GameSer replay = findGame(message);
        if (replay==null){
            replay=findGame2(message);
        }
        Toast.makeText(getApplicationContext(), "replaying "+replay.name, Toast.LENGTH_SHORT).show();
        moves=replay.states.size();

        setContentView(R.layout.replay_board);
        final GridView chessboard = (GridView) findViewById(R.id.chessboard);
        customAdapter = new BoardAdapter(this);


        Button next = findViewById(R.id.next);
        Button exit = findViewById(R.id.exit);

        chessboard.setAdapter(customAdapter);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //customAdapter.cleanBoard();
                Intent intent = new Intent(ReplayGame.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Toast.makeText(getApplicationContext(), moves + "moves", Toast.LENGTH_SHORT).show();

        final GameSer finalReplay = replay;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count<moves){
                    board= finalReplay.states.get(count+1);
                    count+=2;
                    customAdapter.notifyDataSetChanged();
                    return;
                }else {
                    Toast.makeText(getApplicationContext(), "game finished", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
