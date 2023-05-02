package com.example.weatherforecast2022.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast2022.R;
import com.example.weatherforecast2022.models.OPWeather;
import com.example.weatherforecast2022.models.Weather;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WeatherRecyclerViewApdapter extends RecyclerView.Adapter<WeatherRecyclerViewApdapter.WeatherViewHolder> {
     Activity context;
     int layoutId;
     ArrayList<OPWeather> list;
    final String RAIN = "Rain";
    final String CLOUNDS = "Clounds";
    final String CLEAR = "Clear";
    final String UNKNOWN = "Unknown";

    public WeatherRecyclerViewApdapter(Activity context, int layoutId, ArrayList<OPWeather> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
//        return new WeatherViewHolder(viewItem);
        return new WeatherViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRecyclerViewApdapter.WeatherViewHolder holder, int position) {
        final int pos = position;
        // Get data from data source (list)
        OPWeather weather = list.get(pos);
        holder.tvNhietDo.setText(weather.getMain().getTempsString());
        holder.tvTocDoGio.setText(weather.getWind().getSpeed() + " Km/h");
        holder.tvTamNhin.setText(weather.getVisibility() + " m");
        holder.tvThoiGian.setText(weather.getDatetime());
        switch (weather.getWeatherItems().get(0).getWeatherState()) {
            case CLEAR:
                holder.imgThoiTiet.setImageResource(R.drawable.clear);
                break;
            case CLOUNDS:
                holder.imgThoiTiet.setImageResource(R.drawable.clounds);
                break;
            case RAIN:
                holder.imgThoiTiet.setImageResource(R.drawable.rain);
                break;
            default:
                holder.imgThoiTiet.setImageResource(R.drawable.unknown);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
         ImageView imgThoiTiet;
         TextView tvNhietDo;
         TextView tvTocDoGio;
         TextView tvTamNhin;
         TextView tvThoiGian;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThoiTiet = (ImageView) itemView.findViewById(R.id.imgWeather);
            tvNhietDo = (TextView) itemView.findViewById(R.id.tvNhietDo);
            tvTocDoGio = (TextView) itemView.findViewById(R.id.tvTocDoGio);
            tvTamNhin = (TextView) itemView.findViewById(R.id.tvTamNhin);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
        }
    }
}
