package com.example.weatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.model.custommodel.DailyWeatherModel
import com.example.weatherapp.databinding.DailyItemBinding
import com.squareup.picasso.Picasso

class DailyWeatherHolder
    (
    private val binding: DailyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        DailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate(item: DailyWeatherModel) {
        binding.date.text = item.data
        binding.temp.text = item.minTemp.toString()
        binding.maxTemp.text = item.maxTemp.toString()
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + item.icon + "@2x.png")
            .into(binding.image)
    }
}