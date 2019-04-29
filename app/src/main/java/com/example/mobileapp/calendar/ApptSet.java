package com.example.mobileapp.calendar;

public class ApptSet {
    private String day;
    private String month;
    private String year;
    private String startTime;
    private String endTime;
    private String amPmstart;
    private String amPmend;
    private String customerID;
    private String phone;
    private String notes;
    private String stylistKey;

    public ApptSet(String day, String month, String year, String startTime, String endTime, String amPmstart, String amPmend, String customerID, String notes, String Stylistskey, String phone) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amPmstart = amPmstart;
        this.amPmend = amPmend;
        this.notes = notes;
        this.customerID = customerID;
        this.phone = phone;
        this.stylistKey = Stylistskey;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStylistKey() {
        return stylistKey;
    }

    public void setStylistKey(String stylistKey) {
        this.stylistKey = stylistKey;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public void setCustomerID(String newApptname) {
        this.customerID = newApptname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
