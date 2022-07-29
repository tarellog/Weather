package com.example.weatherapp.weather.adapter.hoursweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HoursItemBinding
import com.example.weatherapp.domain.BasedModel
import java.text.SimpleDateFormat
import java.util.*

class HoursWeatherHolder
    (
    private val binding: HoursItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        HoursItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate(item: BasedModel.TimeWeatherModel) {
        val sdf = SimpleDateFormat("HH:mm", Locale("ru"))
        binding.hoursTime.text = sdf.format(item.timeHours)
        binding.hoursTemp.text = binding.root.context.getString(R.string.temp, item.tempHours)
        binding.hoursImage.setImageDrawable(ContextCompat.getDrawable(binding.root.context, item.iconHours.image))
    }
}