package com.example.firebaseintegration;

public class Record {

    private String id;
    private String encodedImage;
    private String hairStyleInfo;
    private String hairStyleDate;

    public Record() {
    }

    public Record(String id, String encodedImage, String hairStyleInfo, String hairStyleDate) {
        this.id = id;
        this.encodedImage = encodedImage;
        this.hairStyleInfo = hairStyleInfo;
        this.hairStyleDate = hairStyleDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String getHairStyleInfo() {
        return hairStyleInfo;
    }

    public void setHairStyleInfo(String hairStyleInfo) {
        this.hairStyleInfo = hairStyleInfo;
    }

    public String getHairStyleDate() {
        return hairStyleDate;
    }

    public void setHairStyleDate(String hairStyleDate) {
        this.hairStyleDate = hairStyleDate;
    }
}
