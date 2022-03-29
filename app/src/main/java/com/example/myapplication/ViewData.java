package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ViewData extends AppCompatActivity {
    Button goToData1;
    Button goToStats;
    TextView name, total2;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        goToData1  = findViewById(R.id.goToData);
        name = findViewById(R.id.welcome);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        goToStats = findViewById(R.id.goToStats);
        total2 = findViewById(R.id.total2);

        getTotal();

        Intent intent = getIntent();
        String username = intent.getStringExtra("regName");
        String username1 = "Välkommen" + " " + username;
        name.setText(username1);

        new DatabaseHelper().readdataList(new DatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<UserHelperClass2> data, List<String> keys) {
                new RecyclerView_Config().setConfig(recyclerView, ViewData.this, data, keys);
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

        goToData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addData.class);

                intent.putExtra("regName", username);

                startActivity(intent);
            }
        });

        goToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStats();
            }
        });

    }

    public void openStats(){
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }

    public void getTotal(){
        DatabaseReference reference = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Total");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String fTotal = dataSnapshot.child("Total").child("total").getValue().toString();
                total2.setText("Totala insamlade pengar " + fTotal + " Kr");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}