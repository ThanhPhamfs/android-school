package com.example.weatherforecast2022.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    String BASE_URL = "https://api.openweathermap.org/";
    @GET("data/2.5/forecast?")
    Call<WeatherWrapper<OPWeather>> getWeathers(
            @Query("q") String city,
            @Query("appid") String apiKey
    );

}
