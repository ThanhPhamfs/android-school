package com.example.weatherforecast2022.models;

public class Weather {
    private String trangThai;
    private NhietDo nhietDo;
    private double tocDoGio;
    private double tamNhin;
    private String thoiGian;

    public Weather() {
    }

    public Weather(String trangThai, NhietDo nhietDo, double tocDoGio, double tamNhin, String thoiGian) {
        this.trangThai = trangThai;
        this.nhietDo = nhietDo;
        this.tocDoGio = tocDoGio;
        this.tamNhin = tamNhin;
        this.thoiGian = thoiGian;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public NhietDo getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(NhietDo nhietDo) {
        this.nhietDo = nhietDo;
    }

    public double getTocDoGio() {
        return tocDoGio;
    }

    public void setTocDoGio(double tocDoGio) {
        this.tocDoGio = tocDoGio;
    }

    public double getTamNhin() {
        return tamNhin;
    }

    public void setTamNhin(double tamNhin) {
        this.tamNhin = tamNhin;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

}
