package com.example.weatherforecast2022.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherWrapper<T> {
    @SerializedName("list")
    private ArrayList<T> items;

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }
}
