package Databases;

import android.provider.BaseColumns;

/**
 * Created by YumnaAsim on 2/3/2018.
 */

public class Schema {

    public static final class Patient implements BaseColumns{

        public static final String TABLE_NAME1 = "Patient";
        public static final String PAT_AGE = "age";
        public static final String PAT_ADDRESS = "address";
        public static final String PAT_MOBILE = "mobile";
        public static final String PAT_OCCUP = "occupation";
        public static final String PAT_GENDER = "gender";
        public static final String PAT_DISTRACTION= "distractedBy";
        public static final String PAT_LANE = "lane";
        public static final String PAT_IS_DISPOSED = "disposal";
        public static final String PAT_STATE = "patientState";
        public static final String PAT_NAME = "name";
        public static final String REP_ID = "report_id";
        public static final String ACC_ID = "accident_id";

    }

    public static final class Report implements BaseColumns{

        public static final String TABLE_NAME2 = "Report";
        public static final String DATE = "date";
        public static final String EMER = "emergencyNo";
        public static final String HOSPITAL = "hospitalName";
        public static final String COLLECTOR = "dataCollectorName";
        public static final String TIMESTAMP = "timestamp";

    }

    public static final class Accident implements BaseColumns{

        public static final String TABLE_NAME3 = "AccidentDetails";
        public static final String ACC_COL_1 = "timeAcc";
        public static final String ACC_COL_2 = "timeArrival";
        public static final String ACC_COL_3 = "arrivalVehicle";
        public static final String ACC_COL_4 = "historyProvider";
        public static final String ACC_COL_5 = "travellingReason";
        public static final String ACC_COL_6 = "vehicle1";
        public static final String ACC_COL_7 = "vehicle2";
        public static final String ACC_COL_8 = "collisionType";
        public static final String ACC_COL_9 = "location";
        public static final String ACC_COL_10 = "locDetails";
        public static final String ACC_COL_11 = "locDescription";
        public static final String ACC_COL_12 = "ambulance";
        public static final String REP_ID = "report_id";
        public static final String PAT_ID = "patient_id";
    }

    public static final class PatientHealth implements BaseColumns{

        public static final String TABLE_NAME4 = "PatientHealth";
        public static final String HEA_COL_1 = "respiratorRate";
        public static final String HEA_COL_2 = "bloodPressure";
        public static final String HEA_COL_3 = "gcs";
        public static final String HEA_COL_4 = "eyeResponse1";
        public static final String HEA_COL_5 = "verbalResponse";
        public static final String HEA_COL_6 = "eyeResponse2";
        public static final String HEA_COL_7 = "headISS";
        public static final String HEA_COL_8 = "chestISS";
        public static final String HEA_COL_9 = "extermityISS";
        public static final String HEA_COL_10 = "faceISS";
        public static final String HEA_COL_11 = "abdomenISS";
        public static final String HEA_COL_12 = "externalISS";
        public static final String HEA_COL_13 = "doctorNotes";
        public static final String PAT_ID = "patient_id";
    }
}
