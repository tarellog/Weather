package com.example.weatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DailyItemBinding
import com.example.weatherapp.domain.BasedModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class DailyWeatherHolder
    (
    private val binding: DailyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        DailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate(item: BasedModel.DailyWeatherModel) {
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.date.text = sdf.format(item.date)
        binding.minTemp.text = binding.root.context.getString(R.string.temp, item.minTemp)
        binding.maxTemp.text = binding.root.context.getString(R.string.temp, item.maxTemp)
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + item.icon + "@2x.png")
            .into(binding.image)

        val hoursAdapter = HoursWeatherAdapter()
        binding.recyclerHours.adapter = hoursAdapter
        hoursAdapter.setData(item.hoursList)
    }





}