package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather.R
import com.example.weather.adapter.hours.HoursAdapter
import com.example.weather.databinding.DailyItemBinding
import com.example.weather.usecases.common.TimeWeather

class WeatherViewHolder(private val binding: DailyItemBinding) : ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        DailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(item: WeatherItem.WeatherData) {
        val context = binding.root.context
        binding.minTemp.text = context.getString(R.string.temp, item.weatherData.minTemp)
        binding.maxTemp.text = context.getString(R.string.temp, item.weatherData.maxTemp)
        createRecyclerWeatherHours(item)
    }

    private fun createRecyclerWeatherHours(item: WeatherItem.WeatherData) {
        val hoursAdapter = HoursAdapter()
        binding.recyclerHours.adapter = hoursAdapter
        hoursAdapter.submitList(item.weatherData.hoursList)
    }

}