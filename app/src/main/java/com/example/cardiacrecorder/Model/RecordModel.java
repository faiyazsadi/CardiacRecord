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

    /**
     * This method is used to get data from database when we retrieve data from database in the recyclerView.
     * @param date
     * @param times
     * @param timestamp
     * @param heartrate
     * @param systolic
     * @param diastolic
     * @param comment
     */

    public RecordModel(String date, String times, String timestamp, String heartrate, String systolic, String diastolic, String comment) {
        this.date = date;
        this.times = times;
        this.timestamp = timestamp;
        this.heartrate = heartrate;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.comment = comment;
    }

    /**
     * This method gets the date which is fetched from database.
     * @return The date put by the user on the record form.
     */
    public String getDate() {
        return date;
    }

    /**
     * This method sets the date which is fetched from database.
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * This method gets the timestamp which is fetched from database.
     * @return The time at which the record was created.
     */
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This method gets the heartrate which is fetched from database.
     * @return The heartrate put by the user on the record form.
     */
    public String getHeartrate() {
        return heartrate;
    }

    /**
     * This method sets the heartrate which is fetched from database.
     * @param heartrate
     */
    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }
    /**
     * This method gets the systolic pressure which is fetched from database.
     * @return The systolic pressure put by the user on the record form.
     */
    public String getSystolic() {
        return systolic;
    }

    /**
     * This method sets the systolic pressure which is fetched from database.
     * @param systolic
     */
    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }
    /**
     * This method gets the diastolic pressure which is fetched from database.
     * @return The diastolic pressure put by the user on the record form.
     */
    public String getDiastolic() {
        return diastolic;
    }

    /**
     * This method sets the diastolic pressure which is fetched from database.
     * @param diastolic
     */
    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }
    /**
     * This method gets the comment which is fetched from database.
     * @return The comment put by the user on the record form.
     */
    public String getComment() {
        return comment;
    }
    /**
     * This method sets the comment which is fetched from database.
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * This method gets the time which is fetched from database.
     * @return The measurement time put by the user on the record form.
     */
    public String getTimes() {
        return times;
    }
    /**
     * This method sets the time which is fetched from database.
     * @param times
     */
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
