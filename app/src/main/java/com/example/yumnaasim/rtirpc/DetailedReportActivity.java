package com.example.yumnaasim.rtirpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;

import Databases.Database;

public class DetailedReportActivity extends AppCompatActivity {

    private static final String TAG = DetailedReportActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_report);

        TextView textView = (TextView) findViewById(R.id.datetimetxt);
        SpannableString content = new SpannableString("Written on Jan 21,2018, 3:52 AM");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        int recordNum = handleIntent();
        displayDataFromDatabase(recordNum);
    }

    private void displayDataFromDatabase(int recordNum) {
        String[] data = new Database(getApplicationContext()).getAccidentData(recordNum);
        for (int i=0;i<12;i++)
        Log.v(TAG,""+data[i]);
        TextView txtview = (TextView) findViewById(R.id.timeArrival);
        TextView txtview2 = (TextView) findViewById(R.id.timeAcc);
        TextView txtview3 = (TextView) findViewById(R.id.ambName);
        TextView txtview4 = (TextView) findViewById(R.id.vehicleArrived);
        TextView txtview5 = (TextView) findViewById(R.id.historyProvider);
        TextView txtview6 = (TextView) findViewById(R.id.travellingReason);
        TextView txtview7 = (TextView) findViewById(R.id.veh1);
        TextView txtview8 = (TextView) findViewById(R.id.veh2);
        TextView txtview9 = (TextView) findViewById(R.id.collisionType);
        TextView txtview10 = (TextView) findViewById(R.id.location);
        TextView txtview11 = (TextView) findViewById(R.id.locDetails);
        TextView txtview12 = (TextView) findViewById(R.id.locDes);

        txtview.setText("  "+data[1]);
        txtview2.setText("  "+data[0]);
        txtview3.setText("  "+data[11]);
        txtview4.setText("  "+data[2]);
        txtview5.setText(data[3]);
        txtview6.setText(data[4]);
        txtview7.setText(data[5]);
        txtview8.setText(data[6]);
        txtview9.setText(data[7]);
        txtview10.setText(data[8]);
        txtview11.setText(data[9]);
        txtview12.setText(data[10]);
    }

    private int handleIntent() {
        Bundle bundle = getIntent().getExtras();
        int recordNumber = (int) bundle.get("RecordNumber");
        return recordNumber;
    }
}
