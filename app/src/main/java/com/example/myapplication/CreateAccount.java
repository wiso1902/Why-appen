package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {
    EditText regName, regPassword, regPassword1;
    Button regBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        regName = findViewById(R.id.usernameC);
        regPassword = findViewById(R.id.passwordC);
        regPassword1 = findViewById(R.id.passwordC1);

        regBtn = findViewById(R.id.createAccount);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/");
                reference = rootNode.getReference("Login");

                String passwordK = regPassword.getText().toString();
                String passwordK1 = regPassword1.getText().toString();
                String userName = regName.getText().toString();

                if (passwordK.equals(passwordK1)){

                    UserHelperClass1 helperClass = new UserHelperClass1(userName,passwordK);
                    reference.child(userName).setValue(helperClass);
                    retrunToLogin();
                    Toast.makeText(CreateAccount.this, "Success!!! ", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(CreateAccount.this, "Password didn't match ", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    public void retrunToLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}