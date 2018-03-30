package com.example.yumnaasim.rtirpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;

import Databases.Database;
import model.CustomAdapter;
import model.Records;

public class MyReports extends AppCompatActivity {
    private static final String TAG = MyReports.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        displayDataInList();
    }

    /*Reading data(time,date) from database and displaying it in the listview layout*/
    private void displayDataInList() {

        String[] data, time;

        ArrayList<Records> arrayList = new ArrayList<>();
        Database database = new Database(getApplicationContext());

        int rows = database.numOfRecord();

        if (rows == 0) {


        } else {

            for (int i = 1; i <= rows; i++) {
                data = database.getData(String.valueOf(i));
                time = data[1].split(" ");
                arrayList.add(new Records(i, data[0], time[1]));
            }
            database.close();
            CustomAdapter customAdapter = new CustomAdapter(this, arrayList);

            ListView listView = (ListView) findViewById(R.id.listview);
            //listView.setAdapter(customAdapter);

            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(customAdapter);
            animationAdapter.setAbsListView(listView);
            listView.setAdapter(animationAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

        }
    }
}
