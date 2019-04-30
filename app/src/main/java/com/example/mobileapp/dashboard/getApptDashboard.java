package com.example.mobileapp.dashboard;

public class getApptDashboard {

    private String notes;
    private String startTime;
    private String endTime;
    private String amPmstart;
    private String amPmend;
    private String customerID;

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAmPmstart() {
        return amPmstart;
    }

    public void setAmPmstart(String amPmstart) {
        this.amPmstart = amPmstart;
    }

    public String getAmPmend() {
        return amPmend;
    }

    public void setAmPmend(String amPmend) {
        this.amPmend = amPmend;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public getApptDashboard(String notes, String startTime, String endTime, String amPmstart, String amPmend, String customerID) {
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amPmstart = amPmstart;
        this.amPmend = amPmend;
        this.customerID = customerID;
    }

    public getApptDashboard() {
    }
}
