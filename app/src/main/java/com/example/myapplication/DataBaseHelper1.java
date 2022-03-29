package com.example.myapplication;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper1 {
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private List<UserHelperClass> data = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<UserHelperClass> data, List<String> keys);
        void DataISInserted();
        void DataIsUpdated();
        void DataISDeleted();
    }

    public DataBaseHelper1() {
        rootNode = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = rootNode.getReference("Data");

    }

    public void readdataList(final DataStatus dataStatus){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    UserHelperClass userHelperClass = keyNode.getValue(UserHelperClass.class);
                    data.add(userHelperClass);
                }
                dataStatus.DataIsLoaded(data, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
