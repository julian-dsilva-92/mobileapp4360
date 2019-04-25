package com.example.calendar_view;

import java.util.ArrayList;

public class ApptDetails {
    private String apptDay;
    private String apptMonth;
    private String apptYear;
    private String clientName;
    private String apptNotes;
    private int apptStarttime;
    private int apptEndtime;
    private String StartAmPm;
    private String EndAmPm;


    public static ArrayList<ApptDetails> apptArray;

    public ApptDetails(String day, String month, String year, int start, int end, String startampm, String endampm,  String name, String notes){

        this.apptDay=day;
        this.apptMonth = month;
        this.apptYear = year;
        this.apptStarttime = start;
        this.apptEndtime = end;
        this.StartAmPm = startampm;
        this.EndAmPm = endampm;
        this.clientName=name;
        this.apptNotes= notes;

    }

    public String getApptDay() {
        return apptDay;
    }

    public void setApptDay(String apptDay) {
        this.apptDay = apptDay;
    }

    public String getApptMonth() {
        return apptMonth;
    }

    public void setApptMonth(String apptMonth) {
        this.apptMonth = apptMonth;
    }

    public String getApptYear() {
        return apptYear;
    }

    public void setApptYear(String apptYear) {
        this.apptYear = apptYear;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public String getApptNotes() {
        return apptNotes;
    }

    public void setApptNotes(String apptNotes) {
        this.apptNotes = apptNotes;
    }

    public int getApptStarttime() {
        return apptStarttime;
    }

    public void setApptStarttime(int apptStarttime) {
        this.apptStarttime = apptStarttime;
    }

    public int getApptEndtime() {
        return apptEndtime;
    }

    public void setApptEndtime(int apptEndtime) {
        this.apptEndtime = apptEndtime;
    }

    public String getStartAmPm() {
        return this.StartAmPm;
    }

    public void setStartAmPm(String amPm) {
        this.StartAmPm = amPm;
    }

    public String getEndAmPm() {
        return EndAmPm;
    }

    public void setEndAmPm(String endAmPm) {
        EndAmPm = endAmPm;
    }


    public String getApptDate() {
        return getApptYear() + "-"+ getApptMonth()+ "-" + getApptDay();
    }

    public String getApptTime(){

       return getApptStarttime() + " " + getStartAmPm() + "- " + getApptEndtime() + " " + getEndAmPm();


    }
}
