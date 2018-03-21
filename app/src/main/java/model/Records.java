package model;

/**
 * Created by Usman on 3/19/2018.
 */

public class Records {
    private int recordNum;
    String date;
    String time;


    public Records(int recordNum, String date, String time) {
        this.recordNum = recordNum;
        this.date = date;
        this.time = time;

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

}
