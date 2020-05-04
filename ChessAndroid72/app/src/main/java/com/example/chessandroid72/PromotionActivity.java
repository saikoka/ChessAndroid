package com.example.chessandroid72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PromotionActivity extends AppCompatActivity {
    private ListView listView;
    private String[] promotionNames;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_dialog);
        listView = findViewById(R.id.promotion_list);
        promotionNames=getResources().getStringArray(R.array.promotion_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.promotion, promotionNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)parent.getItemAtPosition(position);
                Intent intent=getIntent();
                Intent result = new Intent();
                result.putExtra("piece", value);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
