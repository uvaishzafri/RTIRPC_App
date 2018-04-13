package Databases;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import model.AccidentDetails;
import model.AccidentRecord;
import model.Patient;
import model.PatientHealth;

import static neduet.softwareeng.yumnaasim.rtirpc.ShareActivity.TAG;

/**
 * Created by YumnaAsim on 11/4/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "record.db";
    private static final int DB_VER = 1;
    String[] patientData = new String[6];
    String[] accidentData = new String[12];
    String[] patientHealthData = new String[12];

    public Database(Context context) {

        super(context, DB_NAME, null, DB_VER);


    }

    /*this method insert accidentData in the accident record table*/
    public void insertData(Patient patient,AccidentRecord record, AccidentDetails accidentDetails, PatientHealth patientHealth)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.Report.DATE,record.getDate());
        contentValues.put(Schema.Report.EMER,record.getEmergencyNo());
        contentValues.put(Schema.Report.COLLECTOR,record.getDataCollectorName());
        contentValues.put(Schema.Report.HOSPITAL,record.getHospitalName());
        contentValues.put(Schema.Report.TIMESTAMP,record.getTimestamp());

        long recordID = sqLiteDatabase.insert(Schema.Report.TABLE_NAME2,null,contentValues);

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(Schema.Patient.REP_ID,recordID);
        contentValues1.put(Schema.Patient.PAT_ADDRESS,patient.getAddress());
        contentValues1.put(Schema.Patient.PAT_AGE,patient.getAge());
        contentValues1.put(Schema.Patient.PAT_DISTRACTION,patient.getDistractedBy());
        contentValues1.put(Schema.Patient.PAT_GENDER,patient.getGender());
        contentValues1.put(Schema.Patient.PAT_IS_DISPOSED,patient.getDisposal());
        contentValues1.put(Schema.Patient.PAT_LANE,patient.getLane());
        contentValues1.put(Schema.Patient.PAT_MOBILE,patient.getMobile());
        contentValues1.put(Schema.Patient.PAT_OCCUP,patient.getOccupation());
        contentValues1.put(Schema.Patient.PAT_STATE,patient.getPatientState());
        contentValues1.put(Schema.Patient.PAT_NAME,patient.getName());

        long patientID = sqLiteDatabase.insert(Schema.Patient.TABLE_NAME1,null,contentValues1);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(Schema.Accident.ACC_COL_1,accidentDetails.getTimeAccident());
        contentValues2.put(Schema.Accident.ACC_COL_2,accidentDetails.getTimeArrival());
        contentValues2.put(Schema.Accident.ACC_COL_3,accidentDetails.getArrivalVehicle());
        contentValues2.put(Schema.Accident.ACC_COL_4,accidentDetails.getHistoryProvider());
        contentValues2.put(Schema.Accident.ACC_COL_5,accidentDetails.getTravellingReason());
        contentValues2.put(Schema.Accident.ACC_COL_6,accidentDetails.getVehicle1());
        contentValues2.put(Schema.Accident.ACC_COL_7,accidentDetails.getVehicle2());
        contentValues2.put(Schema.Accident.ACC_COL_8,accidentDetails.getCollisionType());
        contentValues2.put(Schema.Accident.ACC_COL_9,accidentDetails.getLocation());
        contentValues2.put(Schema.Accident.ACC_COL_10,accidentDetails.getLocDetails());
        contentValues2.put(Schema.Accident.ACC_COL_11,accidentDetails.getLocDescription());
        contentValues2.put(Schema.Accident.ACC_COL_12,accidentDetails.getAmbulanceName());
        contentValues2.put(Schema.Accident.REP_ID,recordID);
        contentValues2.put(Schema.Accident.PAT_ID,patientID);

        long accidentID = sqLiteDatabase.insert(Schema.Accident.TABLE_NAME3,null,contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(Schema.PatientHealth.HEA_COL_1,patientHealth.getRespiratorRate());
        contentValues3.put(Schema.PatientHealth.HEA_COL_2,patientHealth.getBloodPressure());
        contentValues3.put(Schema.PatientHealth.HEA_COL_3,patientHealth.getGcs());
        contentValues3.put(Schema.PatientHealth.HEA_COL_4,patientHealth.getEyeResponse1());
        contentValues3.put(Schema.PatientHealth.HEA_COL_5,patientHealth.getVerbalResponse1());
        contentValues3.put(Schema.PatientHealth.HEA_COL_6,patientHealth.getEyeResponse2());
        contentValues3.put(Schema.PatientHealth.HEA_COL_7,patientHealth.getHeadISS());
        contentValues3.put(Schema.PatientHealth.HEA_COL_8,patientHealth.getChestISS());
        contentValues3.put(Schema.PatientHealth.HEA_COL_9,patientHealth.getExtermityISS());
        contentValues3.put(Schema.PatientHealth.HEA_COL_10,patientHealth.getFaceISS());
        contentValues3.put(Schema.PatientHealth.HEA_COL_11,patientHealth.getAbdomenISS());
        contentValues3.put(Schema.PatientHealth.HEA_COL_12,patientHealth.getExternalISS());
        contentValues3.put(Schema.PatientHealth.HEA_COL_13,patientHealth.getDoctorNotes());
        contentValues3.put(Schema.Accident.PAT_ID,patientID);

        long healthID = sqLiteDatabase.insert(Schema.PatientHealth.TABLE_NAME4,null,contentValues3);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String tablePatient = "CREATE TABLE "+ Schema.Patient.TABLE_NAME1+
                " ("+Schema.Patient._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Schema.Patient.PAT_NAME+" TEXT,"
                + Schema.Patient.PAT_AGE+" TEXT,"
                + Schema.Patient.PAT_ADDRESS+" TEXT,"
                + Schema.Patient.PAT_MOBILE+" TEXT,"
                + Schema.Patient.PAT_GENDER+ " TEXT,"
                + Schema.Patient.PAT_OCCUP+" TEXT,"
                + Schema.Patient.PAT_STATE+" TEXT,"
                + Schema.Patient.PAT_DISTRACTION+" TEXT,"
                + Schema.Patient.PAT_IS_DISPOSED+" TEXT,"
                + Schema.Patient.PAT_LANE+" TEXT,"
                + Schema.Patient.REP_ID+" INTEGER,"
                + " FOREIGN KEY ("+Schema.Patient.REP_ID+") REFERENCES "+ Schema.Report.TABLE_NAME2+" ("+Schema.Report._ID+") ON UPDATE CASCADE"
                + ");";

        String tableReport = "CREATE TABLE "+ Schema.Report.TABLE_NAME2+
                " ("+Schema.Report._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Schema.Report.DATE+" TEXT,"
                + Schema.Report.EMER+" TEXT,"
                + Schema.Report.HOSPITAL+" TEXT,"
                + Schema.Report.COLLECTOR+" TEXT,"
                + Schema.Report.TIMESTAMP+ " TEXT"
                + ");";

        String tableAccident = "CREATE TABLE "+ Schema.Accident.TABLE_NAME3+
                " ("+Schema.Accident._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Schema.Accident.ACC_COL_1+" TEXT,"
                + Schema.Accident.ACC_COL_2+" TEXT,"
                + Schema.Accident.ACC_COL_3+" TEXT,"
                + Schema.Accident.ACC_COL_4+" TEXT,"
                + Schema.Accident.ACC_COL_5+ " TEXT,"
                + Schema.Accident.ACC_COL_6+" TEXT,"
                + Schema.Accident.ACC_COL_7+" TEXT,"
                + Schema.Accident.ACC_COL_8+" TEXT,"
                + Schema.Accident.ACC_COL_9+" TEXT,"
                + Schema.Accident.ACC_COL_10+" TEXT,"
                + Schema.Accident.ACC_COL_11+" TEXT,"
                + Schema.Accident.ACC_COL_12+" TEXT,"
                + Schema.Accident.REP_ID+" INTEGER,"
                + Schema.Accident.PAT_ID+" INTEGER,"
                + " FOREIGN KEY ("+Schema.Accident.REP_ID+") REFERENCES "+ Schema.Report.TABLE_NAME2+" ("+Schema.Report._ID+") ON UPDATE CASCADE,"
                + " FOREIGN KEY ("+Schema.Accident.PAT_ID+") REFERENCES "+ Schema.Patient.TABLE_NAME1+" ("+Schema.Patient._ID+") ON UPDATE CASCADE"
                + ");";

        String tableHealth = "CREATE TABLE "+ Schema.PatientHealth.TABLE_NAME4+
                " ("+Schema.PatientHealth._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Schema.PatientHealth.HEA_COL_1+" TEXT,"
                + Schema.PatientHealth.HEA_COL_2+" TEXT,"
                + Schema.PatientHealth.HEA_COL_3+" TEXT,"
                + Schema.PatientHealth.HEA_COL_4+" TEXT,"
                + Schema.PatientHealth.HEA_COL_5+ " TEXT,"
                + Schema.PatientHealth.HEA_COL_6+" TEXT,"
                + Schema.PatientHealth.HEA_COL_7+" TEXT,"
                + Schema.PatientHealth.HEA_COL_8+" TEXT,"
                + Schema.PatientHealth.HEA_COL_9+" TEXT,"
                + Schema.PatientHealth.HEA_COL_10+" TEXT,"
                + Schema.PatientHealth.HEA_COL_11+" TEXT,"
                + Schema.PatientHealth.HEA_COL_12+" TEXT,"
                + Schema.PatientHealth.HEA_COL_13+" TEXT,"
                + Schema.PatientHealth.PAT_ID+" INTEGER,"
                + " FOREIGN KEY ("+Schema.PatientHealth.PAT_ID+") REFERENCES "+ Schema.Patient.TABLE_NAME1+" ("+Schema.Patient._ID+") ON UPDATE CASCADE"
                + ");";

        db.execSQL(tableAccident);
        db.execSQL(tableHealth);
        db.execSQL(tablePatient);
        db.execSQL(tableReport);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteData()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int value = sqLiteDatabase.delete(Schema.Patient.TABLE_NAME1,null,null);
        sqLiteDatabase.delete(Schema.Accident.TABLE_NAME3,null,null);
        sqLiteDatabase.delete(Schema.PatientHealth.TABLE_NAME4,null,null);
        sqLiteDatabase.delete(Schema.Report.TABLE_NAME2,null,null);
        Log.v(TAG,""+value);

    }

    public long sizeDatabase()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.getMaximumSize();

    }

    public String dbPath()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.getPath();
    }

    String[] data = new String[2];

    public String[] getDateTimeData(String rowID)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+ Schema.Report.DATE+" FROM "+ Schema.Report.TABLE_NAME2+" WHERE "+ Schema.Report._ID+"="+rowID,null);
        String from[] = { Schema.Report.DATE, Schema.Report.TIMESTAMP };
        String where = Schema.Report._ID + "=?";
        String[] whereArgs = new String[]{rowID+""};
        Cursor cursor = sqLiteDatabase.query(Schema.Report.TABLE_NAME2, from, where, whereArgs, null, null, null, null);

        if(cursor != null)
        {
            while(cursor.moveToNext()){
                data[0] = cursor.getString(cursor.getColumnIndex(Schema.Report.DATE));
                data[1] = cursor.getString(cursor.getColumnIndex(Schema.Report.TIMESTAMP));
            }
        }
        return data;
    }

    public int getTotalNumOfRecords()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, Schema.Report.TABLE_NAME2);
        return numRows;
    }

    String rowID;
    public int getFirstRecordID()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+Schema.Report._ID+" FROM "+Schema.Report.TABLE_NAME2+" ORDER BY "+Schema.Report._ID+" ASC LIMIT 1",null);

        if (cursor.moveToFirst()) {
            rowID = cursor.getString(cursor.getColumnIndex(Schema.Report._ID));
        }
        cursor.close();
        return Integer.parseInt(rowID);
    }



    public String[] getAccidentData(int rowID)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String from[] = { Schema.Accident.ACC_COL_1, Schema.Accident.ACC_COL_2, Schema.Accident.ACC_COL_3, Schema.Accident.ACC_COL_4
                , Schema.Accident.ACC_COL_5, Schema.Accident.ACC_COL_6, Schema.Accident.ACC_COL_7, Schema.Accident.ACC_COL_8
                , Schema.Accident.ACC_COL_9, Schema.Accident.ACC_COL_10, Schema.Accident.ACC_COL_11, Schema.Accident.ACC_COL_12};
        String where = Schema.Accident._ID + "=?";
        String[] whereArgs = new String[]{rowID+""};
        Cursor cursor = sqLiteDatabase.query(Schema.Accident.TABLE_NAME3, from, where, whereArgs, null, null, null, null);

        if(cursor.getCount()>0)
        {
            if (cursor.moveToFirst()) {
                accidentData[0] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_1));
                accidentData[1] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_2));
                accidentData[2] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_3));
                accidentData[3] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_4));
                accidentData[4] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_5));
                accidentData[5] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_6));
                accidentData[6] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_7));
                accidentData[7] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_8));
                accidentData[8] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_9));
                accidentData[9] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_10));
                accidentData[10] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_11));
                accidentData[11] = cursor.getString(cursor.getColumnIndex(Schema.Accident.ACC_COL_12));
            }
            }

        cursor.close();
        sqLiteDatabase.close();
        Log.v(TAG,""+accidentData[1]);
        return accidentData;
    }

/*Read patient data and display it in detail report screen*/
    public String[] getPatientData(int rowID)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String from[] = { Schema.Patient.PAT_NAME,Schema.Patient.PAT_AGE,Schema.Patient.PAT_OCCUP,Schema.Patient.PAT_MOBILE,
                Schema.Patient.PAT_ADDRESS,Schema.Patient.PAT_GENDER};
        String where = Schema.Patient._ID + "=?";
        String[] whereArgs = new String[]{rowID+""};
        Cursor cursor = sqLiteDatabase.query(Schema.Patient.TABLE_NAME1, from, where, whereArgs, null, null, null, null);

        if(cursor.getCount()>0)
        {
            if (cursor.moveToFirst()) {
                patientData[0] = cursor.getString(cursor.getColumnIndex(Schema.Patient.PAT_NAME));
                patientData[1] = cursor.getString(cursor.getColumnIndex(Schema.Patient.PAT_AGE));
                patientData[2] = cursor.getString(cursor.getColumnIndex(Schema.Patient.PAT_OCCUP));
                patientData[3] = cursor.getString(cursor.getColumnIndex(Schema.Patient.PAT_MOBILE));
                patientData[4] = cursor.getString(cursor.getColumnIndex(Schema.Patient.PAT_ADDRESS));
                patientData[5] = cursor.getString(cursor.getColumnIndex(Schema.Patient.PAT_GENDER));
            }
        }

        cursor.close();
        sqLiteDatabase.close();
        return patientData;
    }

    /*Read patient health data and display it in detail report screen*/
    public String[] getPatientHealthData(int rowID)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String from[] = { Schema.PatientHealth.HEA_COL_1,Schema.PatientHealth.HEA_COL_2,Schema.PatientHealth.HEA_COL_3,
                Schema.PatientHealth.HEA_COL_4,Schema.PatientHealth.HEA_COL_6,Schema.PatientHealth.HEA_COL_7,
                Schema.PatientHealth.HEA_COL_8,Schema.PatientHealth.HEA_COL_9,Schema.PatientHealth.HEA_COL_10,
                Schema.PatientHealth.HEA_COL_11,Schema.PatientHealth.HEA_COL_12,Schema.PatientHealth.HEA_COL_13};

        String where = Schema.Patient._ID + "=?";
        String[] whereArgs = new String[]{rowID+""};
        Cursor cursor = sqLiteDatabase.query(Schema.PatientHealth.TABLE_NAME4, from, where, whereArgs, null, null, null, null);

        if(cursor.getCount()>0)
        {
            if (cursor.moveToFirst()) {
                patientHealthData[0] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_1));
                patientHealthData[1] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_2));
                patientHealthData[2] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_3));
                patientHealthData[3] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_4));
                patientHealthData[4] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_6));
                patientHealthData[5] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_7));
                patientHealthData[6] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_8));
                patientHealthData[7] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_9));
                patientHealthData[8] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_10));
                patientHealthData[9] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_11));
                patientHealthData[10] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_12));
                patientHealthData[11] = cursor.getString(cursor.getColumnIndex(Schema.PatientHealth.HEA_COL_13));
            }
        }

        cursor.close();
        sqLiteDatabase.close();
        return patientHealthData;
    }

}