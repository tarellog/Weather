package com.example.weatherapp.weather.adapter.hoursweather

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.domain.BasedModel

class HoursWeatherAdapter : RecyclerView.Adapter<HoursWeatherHolder>() {

    private val list: MutableList<BasedModel.TimeWeatherModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HoursWeatherHolder(parent)

    override fun onBindViewHolder(holder: HoursWeatherHolder, position: Int) {
        holder.populate(list[position])
    }

    override fun getItemCount() = list.size

    fun setData(getList: List<BasedModel.TimeWeatherModel>) {
        list.clear()
        list.addAll(getList)
        notifyDataSetChanged()
    }

}