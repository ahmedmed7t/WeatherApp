package com.medhat.weatherapp.ui.getByLocation;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.data.models.locationWeatherModels.WeatherInfo;
import com.medhat.weatherapp.databinding.WeatherByLocationItemBinding;

import java.util.ArrayList;

public class LocationWeatherRecyclerAdapter extends RecyclerView.Adapter<LocationWeatherRecyclerAdapter.viewHolder> {

    private ArrayList<WeatherInfo> weatherInfoArrayList;

    public LocationWeatherRecyclerAdapter(ArrayList<WeatherInfo> weatherInfoArrayList) {
        this.weatherInfoArrayList = weatherInfoArrayList;
    }

    class viewHolder extends RecyclerView.ViewHolder{

        private WeatherByLocationItemBinding binding;

        public viewHolder(@NonNull WeatherByLocationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        WeatherByLocationItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.weather_by_location_item,parent,false);

        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if(position == weatherInfoArrayList.size()-1){
            holder.binding.setLineVisible(false);
        }else {
            holder.binding.setLineVisible(true);
        }
        holder.binding.setItem(weatherInfoArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherInfoArrayList.size();
    }

}
