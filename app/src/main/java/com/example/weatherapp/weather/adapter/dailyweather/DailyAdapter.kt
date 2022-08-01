package com.example.weatherapp.weather.adapter.dailyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DailyItemBinding
import com.example.weatherapp.weather.adapter.hoursweather.HoursAdapter
import com.example.weatherapp.weather.domain.BasedModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import java.text.SimpleDateFormat
import java.util.*

class DailyAdapter(private val item: BasedModel.DailyWeatherModel) :
    AbstractBindingItem<DailyItemBinding>() {

    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.daily_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): DailyItemBinding {
        return DailyItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: DailyItemBinding, payloads: List<Any>) {
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.date.text = sdf.format(item.date)
        binding.minTemp.text = binding.root.context.getString(R.string.temp, item.minTemp)
        binding.maxTemp.text = binding.root.context.getString(R.string.temp, item.maxTemp)
        binding.image.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                item.icon.image
            )
        )

        val hoursAdapter = ItemAdapter<HoursAdapter>()
        val fastAdapter = FastAdapter.with(hoursAdapter)
        FastAdapterDiffUtil[hoursAdapter] = item.hoursList.map(::HoursAdapter)
        binding.recyclerHours.adapter = fastAdapter
    }

}
