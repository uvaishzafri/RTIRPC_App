package com.example.yumnaasim.rtirpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Databases.Database;
import model.Patient;
import model.PatientHealth;

public class DisplayData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        TextView textView = (TextView) findViewById(R.id.display_data);

        //String data = new Database(getApplicationContext()).getPatientDetails();
       // textView.setText(data);

    }
}
