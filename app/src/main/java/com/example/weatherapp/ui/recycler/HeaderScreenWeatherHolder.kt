package com.example.weatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.custommodel.TodayWeatherModel
import com.example.weatherapp.databinding.HeaderItemBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class HeaderScreenWeatherHolder(
    private val binding: HeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate(item: TodayWeatherModel) {
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.todayDate.text = binding.root.context.getString(
            R.string.today, sdf.format(item.date).toString()
        )
        binding.todayTemp.text = binding.root.context.getString(R.string.temp, item.temp)
        binding.todayDescription.text = item.description
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + item.icon + "@2x.png")
            .into(binding.todayIcon)
    }
}