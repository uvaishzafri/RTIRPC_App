package model;

import java.io.Serializable;

/**
 * Created by YumnaAsim on 11/5/2017.
 */

public class AccidentRecord implements Serializable {

    private int recordID;
    private String date;
    private String emergencyNo;
    private String hospitalName;
    private String dataCollectorName;
    private String timestamp;

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmergencyNo() {
        return emergencyNo;
    }

    public void setEmergencyNo(String emergencyNo) {
        this.emergencyNo = emergencyNo;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDataCollectorName() {
        return dataCollectorName;
    }

    public void setDataCollectorName(String dataCollectorName) {
        this.dataCollectorName = dataCollectorName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
