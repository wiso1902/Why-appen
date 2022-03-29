package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Stats extends AppCompatActivity {
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        new DataBaseHelper1().readdataList(new DataBaseHelper1.DataStatus() {
            @Override
            public void DataIsLoaded(List<UserHelperClass> data, List<String> keys) {
                new RecyclerView_Config1().setConfig(recyclerView, Stats.this, data, keys);
            }

            @Override
            public void DataISInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataISDeleted() {

            }
        });

    }
}