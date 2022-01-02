package com.example.chatin.chatin;


public class Friends {

    public String name, status, image;

    public Friends()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Friends(String name, String status, String image) {
        this.name = name;
        this.status = status;
        this.image = image;
    }
}
