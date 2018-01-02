package com.example.yumnaasim.rtirpc;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import Databases.Database;

public class PatientReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_report);
        ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        Cursor cursor = new Database(getApplicationContext()).getPatientDetails();
        while (cursor.moveToNext())
        {
            String patientName = cursor.getString(1);
            if (!TextUtils.isEmpty(patientName))
            arrayAdapter.add(cursor.getString(1));
        }
    }
}
