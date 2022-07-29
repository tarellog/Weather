package com.example.weatherapp.weather.adapter.hoursweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HoursItemBinding
import com.example.weatherapp.weather.domain.BasedModel
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.text.SimpleDateFormat
import java.util.*

class HoursAdapter(val item: BasedModel.TimeWeatherModel) :
    AbstractBindingItem<HoursItemBinding>() {

    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.hours_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        HoursItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HoursItemBinding, payloads: List<Any>) {
        val sdf = SimpleDateFormat("HH:mm", Locale("ru"))
        binding.hoursTime.text = sdf.format(item.timeHours)
        binding.hoursTemp.text = binding.root.context.getString(R.string.temp, item.tempHours)
        binding.hoursImage.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                item.iconHours.image
            )
        )
    }

}