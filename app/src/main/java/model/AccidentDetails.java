package model;

import java.io.Serializable;

/**
 * Created by YumnaAsim on 11/5/2017.
 */

public class AccidentDetails implements Serializable {
    private String timeArrival;
    private String timeAccident;
    private String ambulanceName;
    private String arrivalVehicle;
    private String historyProvider;
    private String travellingReason;
    private String vehicle1;
    private String vehicle2;
    private String collisionType;
    private String location;
    private String locDetails;
    private String locDescription;

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public String getTimeAccident() {
        return timeAccident;
    }

    public void setTimeAccident(String timeAccident) {
        this.timeAccident = timeAccident;
    }

    public String getArrivalVehicle() {
        return arrivalVehicle;
    }

    public void setArrivalVehicle(String arrivalVehicle) {
        this.arrivalVehicle = arrivalVehicle;
    }

    public String getHistoryProvider() {
        return historyProvider;
    }

    public void setHistoryProvider(String historyProvider) {
        this.historyProvider = historyProvider;
    }

    public String getTravellingReason() {
        return travellingReason;
    }

    public void setTravellingReason(String travellingReason) {
        this.travellingReason = travellingReason;
    }

    public String getVehicle1() {
        return vehicle1;
    }

    public void setVehicle1(String vehicle1) {
        this.vehicle1 = vehicle1;
    }

    public String getVehicle2() {
        return vehicle2;
    }

    public void setVehicle2(String vehicle2) {
        this.vehicle2 = vehicle2;
    }

    public String getCollisionType() {
        return collisionType;
    }

    public void setCollisionType(String collisionType) {
        this.collisionType = collisionType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocDetails() {
        return locDetails;
    }

    public void setLocDetails(String locDetails) {
        this.locDetails = locDetails;
    }

    public String getLocDescription() {
        return locDescription;
    }

    public void setLocDescription(String locDescription) {
        this.locDescription = locDescription;
    }

    public String getAmbulanceName() {
        return ambulanceName;
    }

    public void setAmbulanceName(String ambulanceName) {
        this.ambulanceName = ambulanceName;
    }
}

