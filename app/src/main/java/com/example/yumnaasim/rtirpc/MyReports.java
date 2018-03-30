package com.example.yumnaasim.rtirpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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

        int totalNumOfRows =database.getTotalNumOfRecords()-1;

        if (totalNumOfRows != 0) {
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setVisibility(View.VISIBLE);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
            linearLayout.setVisibility(View.GONE);

            int rowID = database.getFirstRecordID();
            int rows = (totalNumOfRows+rowID);

            for (int i = rowID; i <=rows; i++) {
                data = database.getDateTimeData(String.valueOf(i));
                time = data[1].split(" ");
                arrayList.add(new Records(i, data[0], time[1]));
            }
            database.close();
            CustomAdapter customAdapter = new CustomAdapter(this, arrayList);

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
