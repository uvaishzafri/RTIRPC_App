package com.example.yumnaasim.rtirpc;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.Random;

import Databases.Database;
import Databases.Schema;

public class ExportActivity extends AppCompatActivity {

    public static final String TAG = ExportActivity.class.getSimpleName();
    RadioButton accident;
    RadioButton patient;
    RadioButton health;
    RadioButton report;
    RadioButton database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        this.setTitle("Export");

        accident = (RadioButton) findViewById(R.id.checkBox1);
        patient = (RadioButton) findViewById(R.id.checkBox2);
        health = (RadioButton) findViewById(R.id.checkBox3);
        report = (RadioButton) findViewById(R.id.checkBox4);

        String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        ActivityCompat.requestPermissions(this, permissions, 1);
        handleUserInput();
    }

    private void handleUserInput() {

        Button button = (Button) findViewById(R.id.buttonExport);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(getApplicationContext(),"Please grant permission",Toast.LENGTH_LONG).show();

                }else {

                    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
                    int id = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(id);
                    String selectedButton = radioButton.getText().toString();
                    if (selectedButton.equals(getResources().getString(R.string.file2))) {
                        String path = exportDB(Schema.Accident.TABLE_NAME3, "AccidentData");
                        createNotification(path);
                    } else if (selectedButton.equals(getResources().getString(R.string.file3))) {
                        String path = exportDB(Schema.Patient.TABLE_NAME1, "PatientGeneralData");
                        createNotification(path);
                    } else if (selectedButton.equals(getResources().getString(R.string.file4))) {
                        String path = exportDB(Schema.PatientHealth.TABLE_NAME4, "PatientHealthData");
                        createNotification(path);
                    } else if (selectedButton.equals(getResources().getString(R.string.file1))) {
                        String path = exportDB(Schema.Report.TABLE_NAME2, "ReportData");
                        createNotification(path);
                    } else if (selectedButton.equals(getResources().getString(R.string.file))) {
                        String path3 = exportDB(Schema.Report.TABLE_NAME2, getResources().getString(R.string.file1_name));
                        createNotification(path3);
                        String path2 = exportDB(Schema.PatientHealth.TABLE_NAME4, getResources().getString(R.string.file4_name));
                        createNotification(path2);
                        String path = exportDB(Schema.Patient.TABLE_NAME1, getResources().getString(R.string.file3_name));
                        createNotification(path);
                        String path1 = exportDB(Schema.Accident.TABLE_NAME3, getResources().getString(R.string.file2_name));
                        createNotification(path1);
                    }
                }
            }
        });
    }

    private String exportDB(String tableName, String fileName) {
        Database database = new Database(getApplicationContext());
        SQLiteDatabase sqldb = database.getReadableDatabase(); //My Database class
        Cursor c = null;
        File sdCardDir = Environment.getExternalStorageDirectory();
        String filename = fileName+".csv";
        // the name of the file to export with
        File saveFile = new File(sdCardDir, filename);
        //main code begins here
        try {
            if (fileName.equals("RTIRPC_Database"))
            {
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                queryBuilder.setTables(Schema.Report.TABLE_NAME2+","
                                        +Schema.Accident.TABLE_NAME3+","
                                        +Schema.Patient.TABLE_NAME1+","
                                        +Schema.PatientHealth.TABLE_NAME4);

                queryBuilder.appendWhere(Schema.Accident.TABLE_NAME3+"."+Schema.Accident.REP_ID+" = "+Schema.Report.TABLE_NAME2+"."+Schema.Report._ID+", "
                                        +Schema.Patient.TABLE_NAME1+"."+Schema.Patient.REP_ID+" = "+Schema.Report.TABLE_NAME2+"."+Schema.Report._ID+", "
                                        +Schema.PatientHealth.TABLE_NAME4+"."+Schema.PatientHealth.PAT_ID+" = "+Schema.Report.TABLE_NAME2+"."+Schema.Report._ID);

                String columnsToReturn[] = {
                        Schema.Report.TABLE_NAME2+"."+Schema.Report._ID,
                        Schema.Report.TABLE_NAME2+"."+Schema.Report.DATE,
                        Schema.Report.TABLE_NAME2+"."+Schema.Report.EMER,
                        Schema.Report.TABLE_NAME2+"."+Schema.Report.HOSPITAL,
                        Schema.Report.TABLE_NAME2+"."+Schema.Report.COLLECTOR,
                        Schema.Report.TABLE_NAME2+"."+Schema.Report.HOSPITAL,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_1,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_2,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_3,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_4,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_5,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_6,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_7,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_8,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_9,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_10,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_11,
                        Schema.Accident.TABLE_NAME3+"."+Schema.Accident.ACC_COL_12,
                        Schema.Patient.TABLE_NAME1+"."+Schema.Patient.PAT_GENDER,
                        Schema.PatientHealth.TABLE_NAME4+"."+Schema.PatientHealth.HEA_COL_1

                };

                c = queryBuilder.query(sqldb,columnsToReturn,null,null,null,null,null);
            }
            else{
                c = sqldb.rawQuery("select * from "+ tableName, null);
            }


            int rowcount = 0;
            int colcount = 0;
            FileWriter fw = new FileWriter(saveFile);

            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();
            colcount = c.getColumnCount();
            if (rowcount > 0) {
                c.moveToFirst();

                for (int i = 0; i < colcount; i++) {
                    if (i != colcount - 1) {

                        bw.write(c.getColumnName(i) + ",");

                    } else {

                        bw.write(c.getColumnName(i));

                    }
                }
                bw.newLine();

                for (int i = 0; i < rowcount; i++) {
                    c.moveToPosition(i);

                    for (int j = 0; j < colcount; j++) {
                        if (j != colcount - 1) {
                            bw.write(c.getString(j) + ",");
                        }
                        else
                            bw.write(c.getString(j));
                    }
                    bw.newLine();
                }
                bw.flush();
                Log.i(TAG,"Exported Successfully.");
               // Toast.makeText(getApplicationContext(),"Exported Successfully.",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            if (sqldb.isOpen()) {
                sqldb.close();
                Log.i(TAG,"Error: "+ex.getMessage().toString());
                Toast.makeText(getApplicationContext(),"Error: "+ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }

        } finally {

        }
        database.close();
        return saveFile.getPath();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // save file

            } else {
                Toast.makeText(getApplicationContext(), "Grant permission to complete this action", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createNotification(String path) {
        String[] parts = path.split("/");
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_done_black_24dp)
                        .setContentTitle(""+parts[4])
                        .setContentText("File exported successfully");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String extension = ".csv";
        String type = mime.getMimeTypeFromExtension(extension);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);
        int notify = generateRandom();
        mNotificationManager.notify(notify, mBuilder.build());


    }

    public int generateRandom(){
        Random random = new Random();
        return random.nextInt(9999 - 1000) + 1000;
    }

}
