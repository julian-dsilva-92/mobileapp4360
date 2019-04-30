package com.example.mobileapp.loginregistration;

public class setUser {
    private String fname;
    private String lname;
    private String cellPhone;
    private String userEmail;
    private String pass;


    public setUser(String fname, String lname, String cellPhone, String userEmail, String pass){
        this.fname = fname;
        this.lname = lname;
        this.cellPhone = cellPhone;
        this.userEmail = userEmail;
        this.pass = pass;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
