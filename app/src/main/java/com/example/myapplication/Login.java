package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText usernameGet, passwordGet;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameGet = findViewById(R.id.username);
        passwordGet = findViewById(R.id.password);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        //Button createBtn = (Button) findViewById(R.id.signUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeKeyboard();
                isUser();
                saveLogin();
            }
        });

        /*createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });*/

        loadLogin();
        updateViews();

    }

    public void createAccount(){
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public void isUser(){

        final String username = usernameGet.getText().toString();
        final String password = passwordGet.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Login");

        Query checkUser = reference.orderByChild("regName").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String passwordDB = dataSnapshot.child(username).child("regPassword").getValue(String.class);

                    if(passwordDB.equals(password)){
                        Intent intent = new Intent(getApplicationContext(), ViewData.class);

                        intent.putExtra("regName", username);

                        startActivity(intent);
                    } else {

                        Toast.makeText(Login.this, "Username or Password didn't match", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(Login.this, "Didn't find username", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view!=null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void saveLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, usernameGet.getText().toString());
        editor.apply();

    }

    public void loadLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews(){
        usernameGet.setText(text);
    }

}