package com.example.yumnaasim.rtirpc;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.io.File;

public class ShareActivity extends AppCompatActivity {

    public static final String TAG = ExportActivity.class.getSimpleName();
    RadioButton accident;
    RadioButton patient;
    RadioButton health;
    RadioButton report;
    RadioButton database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        this.setTitle("Share");

        accident = (RadioButton) findViewById(R.id.checkBox1);
        patient = (RadioButton) findViewById(R.id.checkBox2);
        health = (RadioButton) findViewById(R.id.checkBox3);
        report = (RadioButton) findViewById(R.id.checkBox4);
       /* database = (RadioButton) findViewById(R.id.RadioButton5);*/

        String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        ActivityCompat.requestPermissions(this, permissions, 1);
        handleUserInput();
    }

    private void handleUserInput() {

        Button button1 = (Button) findViewById(R.id.buttonShare);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (accident.isChecked()) {
                    shareFile("AccidentDetails");
                }
                if (patient.isChecked()) {
                    shareFile("PatientDetails");
                }
                if (health.isChecked()) {
                    shareFile("PatientHealthDetails");
                }
                if (report.isChecked()) {
                    shareFile("ReportDetails");
                }

            }
        });
    }

    private void shareFile(String fileName) {
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        String path = "/storage/emulated/0/"+fileName+".csv";
        File file = new File(path);
        if(file.exists()) {
            intentShareFile.setType("application/csv");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+path));

            intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                    "RTIRPC Reporting");
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Please find attached file");

            startActivity(Intent.createChooser(intentShareFile, "Share File"));
        }
    }
}
