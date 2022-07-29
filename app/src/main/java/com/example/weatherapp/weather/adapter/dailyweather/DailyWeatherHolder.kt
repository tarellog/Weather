package com.example.weatherapp.weather.adapter.dailyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DailyItemBinding
import com.example.weatherapp.weather.adapter.hoursweather.HoursAdapter
import com.example.weatherapp.weather.domain.BasedModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
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
        binding.image.setImageDrawable(ContextCompat.getDrawable(binding.root.context, item.icon.image))

        val itemAdapter = ItemAdapter<HoursAdapter>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recyclerHours.adapter = fastAdapter
        FastAdapterDiffUtil[itemAdapter] = item.hoursList.map(::HoursAdapter)
    }





}