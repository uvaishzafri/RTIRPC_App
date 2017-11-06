package Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import model.AccidentDetails;
import model.AccidentRecord;
import model.Patient;
import model.PatientHealth;

/**
 * Created by YumnaAsim on 11/4/2017.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "accidentRecord.db";
    private static final int DB_VER = 1;
    private static final String COL_2 = "date";
    private static final String COL_3 = "emergencyNo";
    private static final String COL_4 = "hospitalName";
    private static final String COL_5 = "dataCollectorName";
    private static final String COL_6 = "timestamp";
    private static final String TABLE_NAME = "AccidentRecord";

    private static final String TABLE_NAME1 = "Patient";
    private static final String PAT_COL_1 = "age";
    private static final String PAT_COL_2 = "address";
    private static final String PAT_COL_3 = "mobile";
    private static final String PAT_COL_4 = "occupation";
    private static final String PAT_COL_5 = "gender";
    private static final String PAT_COL_6 = "distractedBy";
    private static final String PAT_COL_7 = "lane";
    private static final String PAT_COL_8 = "disposal";
    private static final String PAT_COL_9 = "vehicle";
    private static final String PAT_COL_10 = "name";

    private static final String TABLE_NAME2 = "PatientHealth";
    private static final String HEA_COL_1 = "respiratorRate";
    private static final String HEA_COL_2 = "bloodPressure";
    private static final String HEA_COL_3 = "gcs";
    private static final String HEA_COL_4 = "eyeResponse1";
    private static final String HEA_COL_5 = "verbalResponse";
    private static final String HEA_COL_6 = "eyeResponse2";
    private static final String HEA_COL_7 = "headISS";
    private static final String HEA_COL_8 = "chestISS";
    private static final String HEA_COL_9 = "extermityISS";
    private static final String HEA_COL_10 = "faceISS";
    private static final String HEA_COL_11 = "abdomenISS";
    private static final String HEA_COL_12 = "externalISS";
    private static final String HEA_COL_13 = "doctorNotes";

    private static final String TABLE_NAME3 = "AccidentDetails";
    private static final String ACC_COL_1 = "timeAcc";
    private static final String ACC_COL_2 = "timeArrival";
    private static final String ACC_COL_3 = "arrivalVehicle";
    private static final String ACC_COL_4 = "historyProvider";
    private static final String ACC_COL_5 = "travellingReason";
    private static final String ACC_COL_6 = "vehicle1";
    private static final String ACC_COL_7 = "vehicle2";
    private static final String ACC_COL_8 = "collisionType";
    private static final String ACC_COL_9 = "location";
    private static final String ACC_COL_10 = "locDetails";
    private static final String ACC_COL_11 = "locDescription";
    private static final String ACC_COL_12 = "ambulance";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    /*this method insert data in the accident record table*/
    public void addToRecord(AccidentRecord record)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = String.format("INSERT INTO "+TABLE_NAME+" ("+COL_2+","+COL_3+","+COL_4+","+COL_5+","+COL_6+")"
                        +" VALUES('%s','%s','%s','%s','%s');",
                record.getDate(),
                record.getEmergencyNo(),
                record.getHospitalName(),
                record.getDataCollectorName(),
                record.getTimestamp());
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }
    /*this method insert data in the accident record table*/
    public void addToPatientInfo(Patient patient)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        /*String query = String.format("INSERT INTO "+TABLE_NAME1+" ("+PAT_COL_10+","+PAT_COL_1+","+PAT_COL_2+","+PAT_COL_3+","+PAT_COL_4+","+PAT_COL_5+","+PAT_COL_6+","+PAT_COL_7+","+PAT_COL_8+","+PAT_COL_9+")"
                        +" VALUES('%s','%s','%s','%s','%s','%s''%s','%s','%s','%s');",*/
        String query = String.format("INSERT INTO "+TABLE_NAME1+" ("+PAT_COL_10+","+PAT_COL_1+","+PAT_COL_2+","+PAT_COL_3+","+PAT_COL_4+")"
                        +" VALUES('%s','%s','%s','%s','%s');",
                patient.getName(),
                patient.getAge(),
                patient.getAddress(),
                patient.getMobile(),
                patient.getOccupation());

      /*          patient.getName(),
                patient.getAge(),
                patient.getAddress(),
                patient.getMobile(),
                patient.getOccupation(),
                patient.getGender(),
                patient.getDistractedBy(),
                patient.getLane(),
                patient.getDisposal(),
                patient.getVehicle());*/
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public void addToPatientHealth(PatientHealth patient)
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
}
