package com.example.weather.adapter.hours

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.weather.usecases.common.TimeWeather

class HoursAdapter : Adapter<HoursViewHolder>() {

    private val list = mutableListOf<TimeWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder =
        HoursViewHolder(parent)

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun submitList(getList: List<TimeWeather>) {
        list.addAll(getList)
    }

}