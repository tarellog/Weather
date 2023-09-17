package com.example.weather.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather.R

class WeatherAdapter : Adapter<ViewHolder>() {

    private val list = mutableListOf<WeatherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            R.layout.header_item -> WeatherHeaderViewHolder(parent)
            R.layout.daily_item -> WeatherViewHolder(parent)
            else -> throw IllegalStateException("view holder not create")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is WeatherHeaderViewHolder -> holder.bind(list[position] as WeatherItem.HeaderData)
            is WeatherViewHolder -> holder.bind(list[position] as WeatherItem.WeatherData)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (list[position]) {
            is WeatherItem.HeaderData -> R.layout.header_item
            is WeatherItem.WeatherData -> R.layout.daily_item
        }

    override fun getItemCount(): Int = list.size

    fun submitList(getList: List<WeatherItem>) {
        list.addAll(getList)
        notifyDataSetChanged()
    }

}