package com.medhat.weatherapp.ui.GetByLocation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherInfo;

import java.util.ArrayList;

public class LocationWeatherRecyclerAdapter extends RecyclerView.Adapter<LocationWeatherRecyclerAdapter.viewHolder> {

    private ArrayList<WeatherInfo> weatherInfoArrayList;

    public LocationWeatherRecyclerAdapter(ArrayList<WeatherInfo> weatherInfoArrayList) {
        this.weatherInfoArrayList = weatherInfoArrayList;
    }

    class viewHolder extends RecyclerView.ViewHolder{

        private TextView dateTime;
        private TextView maxTemp, minTemp, windSpeed;
        private TextView description;
        private ImageView line;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dateTime     = itemView.findViewById(R.id.Weather_By_Location_Date_Time_TextView);
            maxTemp      = itemView.findViewById(R.id.Weather_By_Location_Max_Temp_TextView);
            minTemp      = itemView.findViewById(R.id.Weather_By_Location_Min_Temp_TextView);
            windSpeed    = itemView.findViewById(R.id.Weather_By_Location_Wind_Speed_TextView);
            description  = itemView.findViewById(R.id.Weather_By_Location_Description_TextView);
            line         = itemView.findViewById(R.id.Weather_By_Location_Divider_Line_ImageView);
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_by_location_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if(position == weatherInfoArrayList.size()-1){
            holder.line.setVisibility(View.GONE);
        }else {
            holder.line.setVisibility(View.VISIBLE);
        }

        holder.maxTemp.setText(String.valueOf(weatherInfoArrayList.get(position).getMaxTemp()));
        holder.minTemp.setText(String.valueOf(weatherInfoArrayList.get(position).getMinTemp()));
        holder.windSpeed.setText(String.valueOf(weatherInfoArrayList.get(position).getWindSpeed()));

        holder.description.setText(weatherInfoArrayList.get(position).getWeatherDescription());
        holder.dateTime.setText(weatherInfoArrayList.get(position).getDateTime());
    }

    @Override
    public int getItemCount() {
        return weatherInfoArrayList.size();
    }

}
