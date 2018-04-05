package com.example.yumnaasim.rtirpc;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class ShareActivity extends AppCompatActivity {

    public static final String TAG = ExportActivity.class.getSimpleName();
    RadioButton accident;
    RadioButton patient;
    RadioButton health;
    RadioButton report;
    RadioButton allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        this.setTitle("Share");

        accident = (RadioButton) findViewById(R.id.checkBox1);
        patient = (RadioButton) findViewById(R.id.checkBox2);
        health = (RadioButton) findViewById(R.id.checkBox3);
        report = (RadioButton) findViewById(R.id.checkBox4);
        allData = (RadioButton) findViewById(R.id.allData);

        handleUserInput();
    }

    private void handleUserInput() {

        Button button1 = (Button) findViewById(R.id.buttonShare);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (accident.isChecked()) {
                    shareFile(getResources().getString(R.string.file2_name));
                }
                if (patient.isChecked()) {
                    shareFile(getResources().getString(R.string.file3_name));
                }
                if (health.isChecked()) {
                    shareFile(getResources().getString(R.string.file4_name));
                }
                if (report.isChecked()) {
                    shareFile(getResources().getString(R.string.file1_name));
                }
                if (allData.isChecked()) {
                    shareAllFiles();
                }


            }
        });
    }

    private void shareAllFiles() {
        Intent intentShareFile = new Intent(Intent.ACTION_SEND_MULTIPLE);
        ArrayList<Uri> arrayList = new ArrayList<>();
        String[] filesToSend = {getResources().getString(R.string.file1_name),
                getResources().getString(R.string.file2_name),
                getResources().getString(R.string.file3_name),
                getResources().getString(R.string.file4_name)};

        for (String name : filesToSend) {
            File file = new File("/storage/emulated/0/" + name + ".csv");
            if (file.exists()) {
                Uri uri = Uri.fromFile(file);
                arrayList.add(uri);
            }
        }

        if (arrayList.size() > 0) {
            intentShareFile.setType("application/csv");
            intentShareFile.putParcelableArrayListExtra(Intent.EXTRA_STREAM, arrayList);
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                    "RTIRPC Reporting");
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Please find attached files");
            startActivity(Intent.createChooser(intentShareFile, "Share File"));
        }
        else {
            Toast.makeText(getApplicationContext(), "No file exists. Export data first.", Toast.LENGTH_LONG).show();
        }
    }

    private void shareFile(String fileName) {
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
            String path = "/storage/emulated/0/" + fileName + ".csv";
            File file = new File(path);

            if (file.exists()) {
                intentShareFile.setType("application/csv");
                intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + path));
                intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                        "RTIRPC Reporting");
                intentShareFile.putExtra(Intent.EXTRA_TEXT, "Please find attached file");

                startActivity(Intent.createChooser(intentShareFile, "Share File"));

                }
                else {
                Toast.makeText(getApplicationContext(), "No file exists. Export data first.", Toast.LENGTH_LONG).show();
            }
    }
}
