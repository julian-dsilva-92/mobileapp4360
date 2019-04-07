package com.example.calendar_view;

import java.util.ArrayList;

public class ApptDetails {
    private String apptDate;
    private String clientName;
    private String apptType;
    private String apptNotes;


    public static ArrayList<ApptDetails> apptArray;

    public ApptDetails(String date, String name, String type, String notes){

        this.apptDate=date;
        this.clientName=name;
        this.apptType=type;
        this.apptNotes= notes;

    }

    public String getApptDate() {
        return apptDate;
    }

    public String getClientName() {
        return clientName;
    }

    public String getApptType() {
        return apptType;
    }

    public String getApptNotes() {
        return apptNotes;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public void setApptNotes(String apptNotes) {
        this.apptNotes = apptNotes;
    }
}
