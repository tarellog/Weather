package com.example.weather.adapter.dailyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.databinding.HeaderItemBinding
import com.example.weather.usecases.common.Weather
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.text.SimpleDateFormat
import java.util.*

class HeaderItem(private val item: Weather) :
    AbstractBindingItem<HeaderItemBinding>() {

    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.header_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        HeaderItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HeaderItemBinding, payloads: List<Any>) {
        val context = binding.root.context
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.cityName.text = item.cityName
        binding.todayDate.text = sdf.format(item.headerWeather.first().date).toString()
        binding.todayTemp.text = context.getString(R.string.temp, item.headerWeather.first().temp)
        binding.todayDescription.text = item.headerWeather.first().description
        binding.wind.text = context.getString(R.string.wind, item.headerWeather.first().wind)
        binding.humidity.text = context.getString(R.string.humidity, item.headerWeather.first().humidity)
        binding.todayIcon.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                item.headerWeather.first().icon.image
            )
        )
    }

}
