package com.example.calllog.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSMessage {
    private String sender;
    private String body;
    private String date;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SMSMessage(String sender, String body, String date) {
        this.sender = sender;
        this.body = body;
        this.date = date;
    }

    public SMSMessage() {
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");
        Date dateToConvert = new Date(Long.parseLong(date));
        return sender + " : " + body + " (" + dateFormat.format(dateToConvert) + ")";
    }
}
