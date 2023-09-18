package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather.databinding.WeatherToWeekItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherToWeekViewHolder(private val binding: WeatherToWeekItemBinding) : ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        WeatherToWeekItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(item: WeatherItem.WeatherToWeek) {
        val sdf = SimpleDateFormat("dd MMMM", Locale("ru"))
        val sdfWeek = SimpleDateFormat("EEEE", Locale("ru"))
        binding.week.text = sdfWeek.format(item.toWeekData.date).capitalize(Locale.getDefault())
        binding.date.text = sdf.format(item.toWeekData.date)
        binding.temp.text = item.toWeekData.temp.toString()
        binding.cover.setImageDrawable(
            ContextCompat.getDrawable(binding.root.context, item.toWeekData.icon.image)
        )
    }

}