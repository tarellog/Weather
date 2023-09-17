package com.example.weather.adapter.hours

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather.R
import com.example.weather.databinding.HoursItemBinding
import com.example.weather.usecases.common.TimeWeather
import java.text.SimpleDateFormat
import java.util.Locale

class HoursViewHolder(private val binding: HoursItemBinding) : ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        HoursItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(item: TimeWeather) {
        val context = binding.root.context
        val sdf = SimpleDateFormat("HH:mm", Locale("ru"))
        binding.hoursTime.text = sdf.format(item.timeHours)
        binding.hoursTemp.text = context.getString(R.string.temp, item.tempHours)
        binding.hoursImage.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                item.iconHours.image
            )
        )
    }

}