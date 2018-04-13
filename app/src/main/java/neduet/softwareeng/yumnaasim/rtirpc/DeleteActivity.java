package neduet.softwareeng.yumnaasim.rtirpc;

import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yumnaasim.rtirpc.R;

import java.io.File;

import Databases.Database;

public class DeleteActivity extends AppCompatActivity {
    private static final String TAG = DeleteActivity.class.getSimpleName();
    File accidentData,reportData,patientData,healthData;
    TextView size;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            getWindow().setEnterTransition(new SlideTransition);
        }*/
        size = (TextView) findViewById(R.id.size);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        accidentData = new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file2_name)+".csv");
        reportData = new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file1_name)+".csv");
        patientData = new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file3_name)+".csv");
        healthData = new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file4_name)+".csv");


        getMaxDbSize();
        fileSize(accidentData,reportData,patientData,healthData);

        Button clear = (Button) findViewById(R.id.buttonClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void getMaxDbSize() {
        /*getting maximum database size may grow*/
        Database database = new Database(DeleteActivity.this);

        long maxDbSize = database.sizeDatabase();

        /*Reading current db size*/
        File file = getApplicationContext().getDatabasePath(database.dbPath());
        long currentDbSize = file.length();
        Log.v(TAG,"Current DB size in bytes "+currentDbSize);
        double percentageUsed = (currentDbSize*100)/maxDbSize;
        Log.v(TAG,"Average used "+percentageUsed);


        double multiplicant = 1e-12;
        Log.v(TAG,"Multiplicant is "+multiplicant);
        Log.v(TAG,"Max Database size in bytes is : "+(maxDbSize)+" B");
        maxDbSize *= multiplicant;

        Log.v(TAG,"Max Database size in TB is : "+(maxDbSize)+" TB");

        TextView textView = (TextView) findViewById(R.id.db_size);
        textView.setText(maxDbSize+" TB");

        /*calculating free db size*/
        double curDbSize = currentDbSize * multiplicant;
        double freeDbSize = maxDbSize-curDbSize;
        Log.v(TAG,"Free db size is "+freeDbSize);
        String freeSize = String.format("%.2f",freeDbSize);

        TextView textView1 = (TextView) findViewById(R.id.free_db_size);
        textView1.setText(freeSize+" TB");

        database.close();


    }

    private void fileSize(File accidentData, File reportData, File patientData, File healthData) {
        long totalSize = accidentData.length()+reportData.length()+patientData.length()+healthData.length();
        Log.v(TAG,"File size in bytes is : "+totalSize);
        totalSize = totalSize/1024;
        size.setText(totalSize+" ");

        Log.v(TAG,"File size in KB is : "+totalSize);

        progressBar.setProgress((int) totalSize);
    }

    private void showAlertDialog() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle("Clearing Data")
                .setMessage(getResources().getString(R.string.alert_msg))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database database = new Database(DeleteActivity.this);
                        database.deleteData();
                        long size = database.sizeDatabase();
                        deleteFiles(accidentData,reportData,patientData,healthData);
                        Toast.makeText(getApplicationContext(),"Deleted successfully!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    private void deleteFiles(File accidentData, File reportData, File patientData, File healthData) {
        accidentData.delete();
        reportData.delete();
        patientData.delete();
        healthData.delete();
        TextView size = (TextView) findViewById(R.id.size);
        size.setText(0+" ");
        progressBar.setProgress(0);
    }

}
