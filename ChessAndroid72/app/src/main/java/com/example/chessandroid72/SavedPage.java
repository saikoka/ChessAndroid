package com.example.chessandroid72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.chessandroid72.GameSer.findGame;

public class SavedPage extends AppCompatActivity {
    public static final String choice = null;
    private ListView listView;
    private List<String> savedGames = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainPageActivity.type);

        setContentView(R.layout.savedlist);
        listView = findViewById(R.id.game_list);
        if (message.charAt(0)=='n') {
            for (GameSer gameSer : GameSer.gameSerList) {
                savedGames.add(gameSer.name);
            }
        }
        else if (message.charAt(0)=='d'){
            for (GameSer gameSer : GameSer.gameSerList){
                savedGames.add(gameSer.date);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.promotion, savedGames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString(choice, value);
                Intent intent = new Intent(SavedPage.this, ReplayGame.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}