package com.example.weatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HoursItemBinding
import com.example.weatherapp.domain.BasedModel
import com.squareup.picasso.Picasso
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
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + item.iconHours + "@2x.png")
            .into(binding.hoursImage)
    }

}