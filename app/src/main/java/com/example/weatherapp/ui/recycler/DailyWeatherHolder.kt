package com.example.weatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.databinding.DailyItemBinding
import com.squareup.picasso.Picasso

class DailyWeatherHolder
    (
    private val binding: DailyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        DailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate(item: ListWeatherModel) {
        binding.date.text = item.dt_txt
        binding.temp.text = item.main.temp.toString()
        binding.maxTemp.text = item.main.temp_max.toString()
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + item.weather.first().icon + "@2x.png")
            .into(binding.image)
    }
}