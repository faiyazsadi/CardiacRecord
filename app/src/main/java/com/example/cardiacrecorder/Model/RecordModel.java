package com.example.cardiacrecorder.Model;

public class RecordModel {


//    public String name;

    public String date;
    public String times;
    public String timestamp;
    public String heartrate;
    public String systolic;
    public String diastolic;
    public String comment;

    public RecordModel() {
    }

    public RecordModel(String date, String heartrate) {
        this.date = date;
        this.heartrate = heartrate;
    }


    public RecordModel(String date, String times, String timestamp, String heartrate, String systolic, String diastolic, String comment) {
        this.date = date;
        this.times = times;
        this.timestamp = timestamp;
        this.heartrate = heartrate;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RecordModel rhs = (RecordModel) obj;
        return date == rhs.date && times == rhs.times && heartrate == rhs.heartrate && systolic == rhs.systolic && diastolic == rhs.diastolic && comment == rhs.comment;
    }
}
