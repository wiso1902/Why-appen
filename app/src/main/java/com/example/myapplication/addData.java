package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class addData extends AppCompatActivity {
    Button addData;
    TextView usernameG1, tr;


    private DatePickerDialog datePickerDialog;
    private Button timePicker;
    String money = "20";
    int totalTr = 1;
    String total;


    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference reference2;
    DatabaseReference reference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        tr = findViewById(R.id.DataT);
        usernameG1 = findViewById(R.id.usernameG);

        timePicker = findViewById(R.id.time);
        timePicker.setText(getTodaysDate());

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("regName");
        usernameG1.setText(name1);

        addData = findViewById(R.id.add_Data);
        initDatePicker();

        getMoney();
        getTotal();


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rootNode = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/");
                reference = rootNode.getReference("Data");
                reference2 = rootNode.getReference("Info");
                reference3 = rootNode.getReference("Total");

                String time = timePicker.getText().toString();
                String tr1 = tr.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name1, tr1, time);

                reference.child(name1 + " " + time).setValue(helperClass);

                UserHelperClass3 userHelperClass3 = new UserHelperClass3(name1, money, totalTr);
                reference2.child(name1).setValue(userHelperClass3);

                UserHelperClass4 userHelperClass4 = new UserHelperClass4(total);
                reference3.child("Total").setValue(userHelperClass4);

                Intent intent = new Intent(getApplicationContext(), ViewData.class);
                intent.putExtra("regName", name1);
                startActivity(intent);
                finish();

            }
        });
    }

    public void getTotal(){
        DatabaseReference reference = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Total");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String fTotal = snapshot.child("total").getValue().toString();

                    int total1 = Integer.parseInt(fTotal);
                    total1 = total1 / 20;
                    total1++;
                    total1 = total1 * 20;
                    String newTotal = String.valueOf(total1);

                    total = newTotal;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getMoney(){
        DatabaseReference reference = FirebaseDatabase.getInstance("https://why-app-d8672-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Info");
        Intent intent = getIntent();
        String username = intent.getStringExtra("regName");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String name = snapshot.child("name").getValue().toString();
                    if(username.equals(name)){

                        String newMoney = snapshot.child("money").getValue().toString();

                        int number2;
                        int number = Integer.parseInt(newMoney);
                        number = number/20;
                        number++;
                        number2 = number;
                        number = number *20;

                        String newNuber1 = String.valueOf(number);

                        money = newNuber1;
                        totalTr = number2;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                timePicker.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return day + "-" + getMonthFormat(month) + "-" + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

}
