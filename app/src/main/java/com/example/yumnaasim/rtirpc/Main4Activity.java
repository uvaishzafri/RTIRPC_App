package com.example.yumnaasim.rtirpc;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends Activity {
    private static final String TAG = Main4Activity.class.getSimpleName();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        checkPermission();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Button button = (Button) findViewById(R.id.btnNewCase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this, NavDrawer.class));
                finish();
            }
        });
        Button buttonNotify = (Button) findViewById(R.id.btnNotify);
        buttonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String location = bundle.getString("Location");
                String mode = sharedPreferences.getString(getString(R.string.mode_key),
                        getString(R.string.mode_default));
                Log.v(TAG,mode);
                if (mode.equals("SMS"))
                sendSMS(location);
                else
                    sendEmail(location);
            }
        });
       /* Button button1 = (Button) findViewById(R.id.btnPreview);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,PreviewReport.class));
            }
        });*/
    }

    private void sendEmail(String location) {
        String email = sharedPreferences.getString(getString(R.string.change_email),
                getString(R.string.default_email));
        String[] TO = {email};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Accident Reporting");
        emailIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.notify_sms)+location+getResources().getString(R.string.notfiy_subtxt));

        try {
            startActivity(Intent.createChooser(emailIntent, "Sending ..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Main4Activity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getApplicationContext(),"Email send",Toast.LENGTH_SHORT).show();
    }

    private void sendSMS(String location) {

        String number = sharedPreferences.getString(getString(R.string.change_number),
                getString(R.string.default_number));
        String[] numberDivision = number.split("03");
        number = "+923"+numberDivision[1];
        Log.v(TAG,number);

        SmsManager smsManager = SmsManager.getDefault();
       smsManager.sendTextMessage(number, null, getResources().getString(R.string.notify_sms)+location+getResources().getString(R.string.notfiy_subtxt), null, null);
        Toast.makeText(getApplicationContext(),"SMS send",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      finish();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(Main4Activity.this, new String[] {Manifest.permission.SEND_SMS}, 1);

        }
    }
    }