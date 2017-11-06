package model;

import java.io.Serializable;

/**
 * Created by YumnaAsim on 11/5/2017.
 */

public class PatientHealth implements Serializable{
    private String respiratorRate;
    private String bloodPressure;
    private String gcs;
    private String eyeResponse1;
    private String verbalResponse1;
    private String eyeResponse2;
    private String headISS;
    private String chestISS;
    private String extermityISS;
    private String faceISS;
    private String abdomenISS;
    private String externalISS;
    private String doctorNotes;

    public String getRespiratorRate() {
        return respiratorRate;
    }

    public void setRespiratorRate(String respiratorRate) {
        this.respiratorRate = respiratorRate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getGcs() {
        return gcs;
    }

    public void setGcs(String gcs) {
        this.gcs = gcs;
    }

    public String getEyeResponse1() {
        return eyeResponse1;
    }

    public void setEyeResponse1(String eyeResponse1) {
        this.eyeResponse1 = eyeResponse1;
    }

    public String getVerbalResponse1() {
        return verbalResponse1;
    }

    public void setVerbalResponse1(String verbalResponse1) {
        this.verbalResponse1 = verbalResponse1;
    }

    public String getEyeResponse2() {
        return eyeResponse2;
    }

    public void setEyeResponse2(String eyeResponse2) {
        this.eyeResponse2 = eyeResponse2;
    }

    public String getHeadISS() {
        return headISS;
    }

    public void setHeadISS(String headISS) {
        this.headISS = headISS;
    }

    public String getChestISS() {
        return chestISS;
    }

    public void setChestISS(String chestISS) {
        this.chestISS = chestISS;
    }

    public String getExtermityISS() {
        return extermityISS;
    }

    public void setExtermityISS(String extermityISS) {
        this.extermityISS = extermityISS;
    }

    public String getFaceISS() {
        return faceISS;
    }

    public void setFaceISS(String faceISS) {
        this.faceISS = faceISS;
    }

    public String getAbdomenISS() {
        return abdomenISS;
    }

    public void setAbdomenISS(String abdomenISS) {
        this.abdomenISS = abdomenISS;
    }

    public String getExternalISS() {
        return externalISS;
    }

    public void setExternalISS(String externalISS) {
        this.externalISS = externalISS;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }
}
