package model;

/**
 * Created by Usman on 3/19/2018.
 */

public class Records {
    private int recordNum;
    String date;
    String time;
    String area;

    public Records(int recordNum, String date, String time, String area) {
        this.recordNum = recordNum;
        this.date = date;
        this.time = time;
        this.area = area;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
