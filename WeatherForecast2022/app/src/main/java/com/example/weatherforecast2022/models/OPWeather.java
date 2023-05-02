package com.example.weatherforecast2022.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OPWeather {
    @SerializedName("dt_txt")
    private String datetime;
                                                                                                                                                                                                                                                                                                                                  private int visibility;
    @SerializedName("main")
    private Main main;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("weather")
    private ArrayList<WeatherItem> weatherItems;

    public OPWeather(String datetime, int visibility, Main main, Wind wind, ArrayList<WeatherItem> weatherItems) {
        this.datetime = datetime;
        this.visibility = visibility;
        this.main = main;
        this.wind = wind;
        this.weatherItems = weatherItems;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public ArrayList<WeatherItem> getWeatherItems() {
        return weatherItems;
    }

    public void setWeatherItems(ArrayList<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
    }

    public static class  Wind{
        @SerializedName("speed")
        private float speed;

        public Wind(float speed) {
            this.speed = speed;
        }

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }
    }

    public static class Main{
        @SerializedName("temp")
        private float temp;
        @SerializedName("temp_min")
        private float tempMin;
        @SerializedName("temp_max")
        private float tempMax;

        public Main(float temp, float tempMin, float tempMax) {
            this.temp = temp;
            this.tempMin = tempMin;
            this.tempMax = tempMax;
        }

        public float getTemp() {
            return temp - 273.15f;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getTempMin() {
            return tempMin - 273.15f;
        }

        public void setTempMin(float tempMin) {
            this.tempMin = tempMin;
        }

        public float getTempMax() {
            return tempMax - 273.15f;
        }

        public void setTempMax(float tempMax) {
            this.tempMax = tempMax;
        }

        public String getTempsString() {
            return "Max: " + ((int)(getTempMax() + 0.5)) + " Now: " + ((int)(getTemp() + 0.5))  + " Min: "+ ((int)(getTempMin() + 0.5)) ;
        }
    }

    public static class WeatherItem{
        @SerializedName("main")
        private String weatherState;

        public WeatherItem(String weatherState) {
            this.weatherState = weatherState;
        }

        public String getWeatherState() {
            return weatherState;
        }

        public void setWeatherState(String weatherState) {
            this.weatherState = weatherState;
        }
    }
}
