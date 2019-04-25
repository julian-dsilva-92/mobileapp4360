package com.mobileapp.newappt;

public class ApptSet {
    private String day;
    private  String month;
    private  String year;
    private int startTime;
    private int endTime;
    private String amPmstart;
    private String amPmend;
    private String newApptname;
    private String phone;
    private String notes;
    private int stylistKey;

    public ApptSet(String day, String month, String year, int startTime, int endTime, String amPmstart, String amPmend, String name, String phone, String notes, int Stylistskey) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amPmstart = amPmstart;
        this.amPmend = amPmend;
        this.notes = notes;
        this.newApptname = name;
        this.phone = phone;
        this.stylistKey = Stylistskey;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStylistKey() {
        return stylistKey;
    }

    public void setStylistKey(int stylistKey) {
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
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

    public String getNewApptname() {
        return newApptname;
    }

    public void setNewApptname(String newApptname) {
        this.newApptname = newApptname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
