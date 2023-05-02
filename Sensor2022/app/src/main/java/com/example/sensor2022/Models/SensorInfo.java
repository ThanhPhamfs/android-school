package com.example.sensor2022.Models;

public class SensorInfo {
    private String ten;
    private String nSX;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getnSX() {
        return nSX;
    }

    public void setnSX(String nSX) {
        this.nSX = nSX;
    }

    public SensorInfo(String ten, String nSX) {
        this.ten = ten;
        this.nSX = nSX;
    }

    public SensorInfo() {
    }

    @Override
    public String toString() {
        return ten + " : " + nSX;
    }
}
