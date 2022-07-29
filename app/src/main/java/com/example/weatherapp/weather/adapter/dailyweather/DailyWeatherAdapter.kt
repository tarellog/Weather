package com.example.weatherapp.weather.adapter.dailyweather

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.domain.BasedModel

class DailyWeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: MutableList<BasedModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType) {
            0 -> DailyWeatherHolder(parent)
            else -> HeaderScreenWeatherHolder(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DailyWeatherHolder -> holder.populate(list[position] as BasedModel.DailyWeatherModel)
            is HeaderScreenWeatherHolder -> holder.populate(list[position] as BasedModel.TodayWeatherModel)
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when(list[position]) {
            is BasedModel.DailyWeatherModel -> 0
            else -> 1
        }
    }

    fun setData(getList: List<BasedModel>) {
        list.clear()
        list.addAll(getList)
        notifyDataSetChanged()
    }

}