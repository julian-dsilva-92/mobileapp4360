package com.example.mobileapp.camera;

public class Record {
    private String id;
    private String encodedImage;
    private String hairStyleInfo;
    private String hairStyleDate;
    private String customerPhNo;

    public Record() {
    }

    public Record(String id, String encodedImage, String hairStyleInfo, String hairStyleDate, String customerPhNo) {
        this.id = id;
        this.encodedImage = encodedImage;
        this.hairStyleInfo = hairStyleInfo;
        this.hairStyleDate = hairStyleDate;
        this.customerPhNo = customerPhNo;
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

    public String getCustomerPhNo() {
        return customerPhNo;
    }

    public void setCustomerPhNo(String customerPhNo) {
        this.customerPhNo = customerPhNo;
    }
}
