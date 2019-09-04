package com.example.firebase_db;

public class Student {

    private String ID;
    private String name;
    private String address;
    private int ConNo;

    public int getConNo() {
        return ConNo;
    }

    public void setConNo(int conNo) {
        ConNo = conNo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
