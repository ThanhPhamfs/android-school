package com.example.weatherforecast2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.weatherforecast2022.Adapters.WeatherRecyclerViewApdapter;
import com.example.weatherforecast2022.models.NhietDo;
import com.example.weatherforecast2022.models.OPWeather;
import com.example.weatherforecast2022.models.Weather;
import com.example.weatherforecast2022.models.WeatherAPI;
import com.example.weatherforecast2022.models.WeatherWrapper;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvWeather;
    private Spinner spinnerLocation;
    private WeatherRecyclerViewApdapter adapter;
    //    private ArrayList<Weather> list = new ArrayList<>();
    private ArrayList<OPWeather> list = new ArrayList<>();
    private WeatherAPI weatherAPI;

//    private ArrayList<Weather> dummyData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////        Dummy data
//        Weather weather1 = new Weather(RAIN, new NhietDo(25, 24, 23), 5.8, 10000, "2021-02-08 18:00:00");
//        Weather weather2 = new Weather(CLOUNDS, new NhietDo(24, 20, 19), 4, 10000, "2021-02-08 18:00:00");
//        Weather weather3 = new Weather(CLEAR, new NhietDo(29, 24, 20), 3.24, 10000, "2021-02-08 18:00:00");
//        Weather weather4 = new Weather("NN", new NhietDo(30, 24, 23), 2.3, 10000, "2021-02-08 18:00:00");
//        Weather weather5 = new Weather(RAIN, new NhietDo(25, 24, 23), 6.32, 20000, "2021-02-08 18:00:00");
//        Weather weather6 = new Weather(CLOUNDS, new NhietDo(28, 24, 23), 4.2, 10000, "2021-02-08 18:00:00");
//        Weather weather7 = new Weather(CLEAR, new NhietDo(24, 21, 19), 3.24, 10000, "2021-02-08 18:00:00");
//        Weather weather8 = new Weather(CLOUNDS, new NhietDo(33, 30, 28), 2.5, 10000, "2021-02-08 18:00:00");
//        dummyData.add(weather1);
//        dummyData.add(weather2);
//        dummyData.add(weather3);
//        dummyData.add(weather4);
//        dummyData.add(weather5);
//        dummyData.add(weather6);
//        dummyData.add(weather7);
//        dummyData.add(weather8);

//        RecyclerView Weather
        rvWeather = findViewById(R.id.rvWeather);
        rvWeather.setHasFixedSize(true);
        adapter = new WeatherRecyclerViewApdapter(this, R.layout.weather_item, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvWeather.setLayoutManager(layoutManager);
        rvWeather.setAdapter(adapter);

//        Spinner location
        spinnerLocation = findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.locations, android.R.layout.simple_list_item_1);
        spinnerLocation.setAdapter(arrayAdapter);
        spinnerLocation.setOnItemSelectedListener(spinnerSelectedListener);
    }

    private void getWeatherData(String city) {
        list.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherAPI = retrofit.create(WeatherAPI.class);

        Call<WeatherWrapper<OPWeather>> call = weatherAPI.getWeathers(city, getResources().getString(R.string.open_weather_api_key));
        call.enqueue(new Callback<WeatherWrapper<OPWeather>>() {
            @Override
            public void onResponse(Call<WeatherWrapper<OPWeather>> call, Response<WeatherWrapper<OPWeather>> response) {
                if (response.isSuccessful()) {
                    WeatherWrapper<OPWeather> weathers = response.body();
                    Toast.makeText(MainActivity.this, weathers.getItems().size() + "", Toast.LENGTH_SHORT).show();
//                    If weathers null throw out
                    assert weathers != null;
                    list.addAll(weathers.getItems());
                    adapter.notifyDataSetChanged();
                    return;
                }
                Log.d("ErrorF", response.message());
            }

            @Override
            public void onFailure(Call<WeatherWrapper<OPWeather>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selectedValue = adapterView.getSelectedItem().toString();
//            String[] arrayLoctions = getResources().getStringArray(R.array.locations);
            getWeatherData(selectedValue);
//            Fetch values
//            list.clear();
//            adapter.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}