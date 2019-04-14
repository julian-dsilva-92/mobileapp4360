package com.example.firebaseintegration;

public class Record {

    private String id;
    private String encodedImage;

    public Record() {
    }

    public Record(String id, String encodedImage) {
        this.id = id;
        this.encodedImage = encodedImage;
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
}
