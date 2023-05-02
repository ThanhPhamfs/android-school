package com.example.calllog.Models;

public class CallLog {
    private String duration;
    private String number;
    private String contryIso;
    private String type;
    private String date;

    public CallLog() {
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContryIso() {
        return contryIso;
    }

    public void setContryIso(String contryIso) {
        this.contryIso = contryIso;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CallLog(String duration, String number, String contryIso, String type, String date) {
        this.duration = duration;
        this.number = number;
        this.contryIso = contryIso;
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return "CallLog{" +
                "duration='" + duration + '\'' +
                ", number='" + number + '\'' +
                ", contryIso='" + contryIso + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
