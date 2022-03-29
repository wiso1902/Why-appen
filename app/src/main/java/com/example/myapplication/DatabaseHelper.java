package com.example.myapplication;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private List<UserHelperClass2> data = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<UserHelperClass2> data, List<String> keys);
        void DataISInserted();
        void DataIsUpdated();
        void DataISDeleted();
    }
    public DatabaseHelper() {
        rootNode = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = rootNode.getReference("Info");

    }

    public void readdataList(final DataStatus dataStatus){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    UserHelperClass2 userHelperClass2 = keyNode.getValue(UserHelperClass2.class);
                    data.add(userHelperClass2);
                }
                dataStatus.DataIsLoaded(data, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

