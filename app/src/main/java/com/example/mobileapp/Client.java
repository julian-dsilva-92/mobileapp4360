package com.example.mobileapp;

public class Client {

    private String firstName;
    private String lastName;
    private String description;
    private String phoneNumber;
    private String encodedImage;

    public Client(){

    }

    public Client(String firstName, String lastName, String description, String phoneNumber, String encodedImage){
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.encodedImage = encodedImage;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEncodedImage(){
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage){
        this.encodedImage = encodedImage;
    }






}
