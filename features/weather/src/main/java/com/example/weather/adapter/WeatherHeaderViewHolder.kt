package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather.R
import com.example.weather.databinding.HeaderItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherHeaderViewHolder(private val binding: HeaderItemBinding) : ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(item: WeatherItem.HeaderData) {
        val context = binding.root.context
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.cityName.text = item.headerData.cityName
        binding.todayDate.text = sdf.format(item.headerData.date).toString()
        binding.todayTemp.text = context.getString(R.string.temp, item.headerData.temp)
        binding.todayDescription.text = item.headerData.description
        binding.wind.text = context.getString(R.string.wind, item.headerData.wind)
        binding.humidity.text = context.getString(R.string.humidity, item.headerData.humidity)
        binding.todayIcon.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                item.headerData.icon.image
            )
        )
    }

}