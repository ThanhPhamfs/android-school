package com.example.weatherforecast2022.models;

public class NhietDo {
    private int max;
    private int now;
    private int min;

    public NhietDo() {
    }

    public NhietDo(int max, int now, int min) {
        this.max = max;
        this.now = now;
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Max: " + max + " Now: " + now + " Min: "+ min;
    }
}
