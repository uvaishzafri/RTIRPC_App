package model;

import java.io.Serializable;

/**
 * Created by YumnaAsim on 11/5/2017.
 */

public class Patient implements Serializable {

    private String name;
    private String age;
    private String address;
    private String mobile;
    private String occupation;
    private String gender;
    private String distractedBy;
    private String lane;
    private String disposal;
    private String patientState;

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDistractedBy(String distractedBy) {
        this.distractedBy = distractedBy;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public void setDisposal(String disposal) {
        this.disposal = disposal;
    }



    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getGender() {
        return gender;
    }

    public String getDistractedBy() {
        return distractedBy;
    }

    public String getLane() {
        return lane;
    }

    public String getDisposal() {
        return disposal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }
}
