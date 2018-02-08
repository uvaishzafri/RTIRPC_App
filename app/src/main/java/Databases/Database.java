package Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import model.AccidentDetails;
import model.AccidentRecord;
import model.Patient;
import model.PatientHealth;

/**
 * Created by YumnaAsim on 11/4/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "record.db";
    private static final int DB_VER = 1;

    public Database(Context context) {

        super(context, DB_NAME, null, DB_VER);

    }

    /*this method insert data in the accident record table*/
/*    public void addToRecord(AccidentRecord record)
    {


       *//* String query = String.format("INSERT INTO "+TABLE_NAME+" ("+COL_2+","+COL_3+","+COL_4+","+COL_5+","+COL_6+")"
                        +" VALUES('%s','%s','%s','%s','%s');",
                record.getDate(),
                record.getEmergencyNo(),
                record.getHospitalName(),
                record.getDataCollectorName(),
                record.getTimestamp());
        sqLiteDatabase.execSQL(query);*//*
      *//*  sqLiteDatabase.close();*//*
    }*/
    /*this method insert data in the accident record table*/
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

        /*String query = String.format("INSERT INTO "+TABLE_NAME1+" ("+PAT_COL_10+","+PAT_COL_1+","+PAT_COL_2+","+PAT_COL_3+","+PAT_COL_4+","+PAT_COL_5+","+PAT_COL_6+","+PAT_COL_7+","+PAT_COL_8+","+PAT_COL_9+")"
                        +" VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                patient.getName(),
                patient.getAge(),
                patient.getAddress(),
                patient.getMobile(),
                patient.getOccupation(),
                patient.getGender(),
                patient.getDistractedBy(),
                patient.getLane(),
                patient.getDisposal(),
                patient.getPatientState());
        sqLiteDatabase.execSQL(query);*/
       /* sqLiteDatabase.close();
        String query = String.format("INSERT INTO "+TABLE_NAME1+" ("+PAT_COL_10+","+PAT_COL_1+","+PAT_COL_2+","+PAT_COL_3+","+PAT_COL_4+")"
                        +" VALUES('%s','%s','%s','%s','%s');",
                patient.getName(),
                patient.getAge(),
                patient.getAddress(),
                patient.getMobile(),
                patient.getOccupation());*/
    }

 /*   public void addToPatientHealth(PatientHealth patient)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = String.format("INSERT INTO "+TABLE_NAME2+" ("+HEA_COL_1+","+HEA_COL_2+","+HEA_COL_3+","+HEA_COL_4+","+HEA_COL_5+","+HEA_COL_6+","+HEA_COL_7+","+HEA_COL_8+","+HEA_COL_9+","+HEA_COL_10+","+HEA_COL_11+","+HEA_COL_12+","+HEA_COL_13+")"
                        +" VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                patient.getRespiratorRate(),
                patient.getBloodPressure(),
                patient.getGcs(),
                patient.getEyeResponse1(),
                patient.getVerbalResponse1(),
                patient.getEyeResponse2(),
                patient.getHeadISS(),
                patient.getChestISS(),
                patient.getExtermityISS(),
                patient.getFaceISS(),
                patient.getAbdomenISS(),
                patient.getExternalISS(),
                patient.getDoctorNotes()
        );
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public void addToAccidentDetails(AccidentDetails details)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = String.format("INSERT INTO "+TABLE_NAME3+" ("+ACC_COL_1+","+ACC_COL_2+","+ACC_COL_3+","+ACC_COL_4+","+ACC_COL_5+","+ACC_COL_6+","+ACC_COL_7+","+ACC_COL_8+","+ACC_COL_9+","+ACC_COL_10+","+ACC_COL_11+","+ACC_COL_12+")"
                        +" VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
             details.getTimeAccident(),
                details.getTimeArrival(),
                details.getArrivalVehicle(),
                details.getAmbulanceName(),
                details.getHistoryProvider(),
                details.getTravellingReason(),
                details.getVehicle1(),
                details.getVehicle2(),
                details.getCollisionType(),
                details.getLocation(),
                details.getLocDetails(),
                details.getLocDescription()
        );
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public PatientHealth getPatientHealthDetails(){
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer1 = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +TABLE_NAME2+" " ,null);

        while(cursor.moveToNext()){
            stringBuffer.append(cursor.getString(2)).append("\n ");
            stringBuffer1.append(cursor.getString(13)).append("\n ");

        }
        String bloodPressure = stringBuffer.toString();
        PatientHealth patientHealth = new PatientHealth();
        patientHealth.setBloodPressure(bloodPressure);
        patientHealth.setDoctorNotes(stringBuffer1.toString());
        sqLiteDatabase.close();
        return patientHealth;
    }
    public Cursor getPatientDetails(){

        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +TABLE_NAME1+" " ,null);

      *//*  while(cursor.moveToNext()){
            stringBuffer.append(cursor.getString(1)).append("   ")
                    .append(cursor.getString(2)).append("   ")
                    .append(cursor.getString(3)).append("   ")
                    .append(cursor.getString(4)).append("   ")
                    .append("\n");

        }

        sqLiteDatabase.close();
        return stringBuffer.toString();*//*
      return cursor;

    }*/

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
}