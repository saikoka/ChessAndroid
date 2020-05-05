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

public class SavedPage extends AppCompatActivity {
    private ListView listView;
    private List<String> savedGames = new ArrayList<>();





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savedlist);
        listView = findViewById(R.id.game_list);

        savedGames.add("temp");
        savedGames.add("second temp");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.promotion, savedGames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SavedPage.this, ReplayGame.class);
                startActivity(intent);
            }
        });
    }
}