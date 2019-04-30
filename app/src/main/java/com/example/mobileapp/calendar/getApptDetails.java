package com.example.mobileapp.calendar;

public class getApptDetails {

    private String amPmend;
    private String amPmstart;
    private String day;
    private String endTime;
    private String month;
    private String notes;
    private String startTime;
    private String year;
    private String customerID;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public getApptDetails(String amPmend, String amPmstart, String day, String endTime, String month, String notes, String startTime, String year) {
        this.amPmend = amPmend;
        this.amPmstart = amPmstart;
        this.day = day;
        this.endTime = endTime;
        this.month = month;
        this.notes = notes;
        this.startTime = startTime;
        this.year = year;
    }


    public getApptDetails(){

    }

    public String getAmPmend() {
        return amPmend;
    }

    public void setAmPmend(String amPmend) {
        this.amPmend = amPmend;
    }

    public String getAmPmstart() {
        return amPmstart;
    }

    public void setAmPmstart(String amPmstart) {
        this.amPmstart = amPmstart;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
