package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    TextView val;
    DatabaseReference dbref;
    String status;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val = (TextView) findViewById(R.id.value);
        mDatabaseHelper = new DatabaseHelper(this);
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                status = dataSnapshot.child("MOISTURE_READ").getValue().toString();
                val.setText(status);
                if (val.length() != 0) {
                    AddData(status);
                   // editText.setText("");
                } else {
                    //Toast.makeText(this, "Unable to read value",1).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Toast.makeText(this, "Unable to read value", Toast.LENGTH_LONG).show();
            }
        });
    }
        public void AddData(String status){
            boolean insertData = mDatabaseHelper.addData(status);
            if(insertData){
                Toast.makeText(this,"Data Inserted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }

    }

