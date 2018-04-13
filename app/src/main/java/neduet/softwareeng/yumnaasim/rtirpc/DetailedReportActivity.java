package neduet.softwareeng.yumnaasim.rtirpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;

import com.example.yumnaasim.rtirpc.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Databases.Database;

public class DetailedReportActivity extends AppCompatActivity {

    private static final String TAG = DetailedReportActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_report);

        int recordNum = handleIntent();
        Log.v(TAG,"Record number is "+recordNum);

        displayDataFromDatabase(recordNum);
    }

    private void displayDataFromDatabase(int recordNum){

        Database database = new Database(getApplicationContext());

        String[] dateTime = database.getDateTimeData(String.valueOf(recordNum));
        String[] time = dateTime[1].split(" ");
        Log.v(TAG,"DateTime is "+dateTime);
        Log.v(TAG,"time is "+time);

        String newDateString = convertDateStringFormat(dateTime[1], "dd-MM-yyy hh:mm:ss", "EEE, MMM d, ''yy hh:mm a");

        TextView textView = (TextView) findViewById(R.id.datetimetxt);
        SpannableString content = new SpannableString("Written on "+newDateString);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);


        String[] data = database.getAccidentData(recordNum);

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
        txtview5.setText("  "+data[3]);
        txtview6.setText("  "+data[4]);
        txtview7.setText("  "+data[5]);
        txtview8.setText("  "+data[6]);
        txtview9.setText("  "+data[7]);
        txtview10.setText("  "+data[8]);
        txtview11.setText("  "+data[9]);
        txtview12.setText("  "+data[10]);

        String[] patientData = database.getPatientData(recordNum);

        TextView txtview13 = (TextView) findViewById(R.id.name);
        TextView txtview14 = (TextView) findViewById(R.id.age);
        TextView txtview15 = (TextView) findViewById(R.id.gender);
        TextView txtview16 = (TextView) findViewById(R.id.address);
        TextView txtview17 = (TextView) findViewById(R.id.mobile);
        TextView txtview18 = (TextView) findViewById(R.id.occupation);

        txtview13.setText("  "+patientData[0]);
        txtview14.setText("  "+patientData[1]);
        txtview15.setText("  "+patientData[5]);
        txtview16.setText("  "+patientData[4]);
        txtview17.setText("  "+patientData[3]);
        txtview18.setText("  "+patientData[2]);

        String[] patientHealthData = database.getPatientHealthData(recordNum);

        TextView txtview19 = (TextView) findViewById(R.id.respiratorRate);
        TextView txtview20 = (TextView) findViewById(R.id.bloodPressure);
        TextView txtview21 = (TextView) findViewById(R.id.gcs);
        TextView txtview22 = (TextView) findViewById(R.id.eyeRes1);
        TextView txtview23 = (TextView) findViewById(R.id.eyeRes2);
        TextView txtview24 = (TextView) findViewById(R.id.headISS);
        TextView txtview25 = (TextView) findViewById(R.id.chestISS);
        TextView txtview26 = (TextView) findViewById(R.id.extermityISS);
        TextView txtview27 = (TextView) findViewById(R.id.faceISS);
        TextView txtview28 = (TextView) findViewById(R.id.abdomenISS);
        TextView txtview29 = (TextView) findViewById(R.id.extISS);
        TextView txtview30 = (TextView) findViewById(R.id.doctor_notes);

        txtview19.setText("  "+patientHealthData[0]);
        txtview20.setText("  "+patientHealthData[1]);
        txtview21.setText("  "+patientHealthData[2]);
        txtview22.setText("  "+patientHealthData[3]);
        txtview23.setText("  "+patientHealthData[4]);
        txtview24.setText("  "+patientHealthData[5]);
        txtview25.setText("  "+patientHealthData[6]);
        txtview26.setText("  "+patientHealthData[7]);
        txtview27.setText("  "+patientHealthData[8]);
        txtview28.setText("  "+patientHealthData[9]);
        txtview29.setText("  "+patientHealthData[10]);
        txtview30.setText("  "+patientHealthData[11]);

    }

    private int handleIntent() {
        Bundle bundle = getIntent().getExtras();
        int recordNumber = (int) bundle.get("RecordNumber");
        return recordNumber;
    }

    public static String convertDateStringFormat(String dateString, String originalDateFormat, String outputDateFormat){
        String finalDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(originalDateFormat);
        try {
            Date date = simpleDateFormat.parse(dateString);
            simpleDateFormat = new SimpleDateFormat(outputDateFormat);
            finalDate = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.v(TAG,"Data after format change is "+finalDate);
        return finalDate;
    }
}
