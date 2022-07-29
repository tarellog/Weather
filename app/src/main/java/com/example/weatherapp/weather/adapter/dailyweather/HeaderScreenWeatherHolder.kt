package com.example.weatherapp.weather.adapter.dailyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HeaderItemBinding
import com.example.weatherapp.weather.domain.BasedModel
import java.text.SimpleDateFormat
import java.util.*

class HeaderScreenWeatherHolder(
    private val binding: HeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate(item: BasedModel.TodayWeatherModel) {
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.todayDate.text = binding.root.context.getString(
            R.string.today, sdf.format(item.date).toString()
        )
        binding.todayTemp.text = binding.root.context.getString(R.string.temp, item.temp)
        binding.todayDescription.text = item.description
        binding.todayIcon.setImageDrawable(ContextCompat.getDrawable(binding.root.context, item.icon.image))
    }
}