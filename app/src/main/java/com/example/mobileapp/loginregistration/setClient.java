package com.example.mobileapp.loginregistration;

public class setClient {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String notes;
    private String hairstylistID;


    public setClient(String firstName, String lastName, String phone, String email, String notes, String hairstylistID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
        this.hairstylistID = hairstylistID;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHairstylistID() {
        return hairstylistID;
    }

    public void setHairstylistID(String hairstylistID) {
        this.hairstylistID = hairstylistID;
    }
}