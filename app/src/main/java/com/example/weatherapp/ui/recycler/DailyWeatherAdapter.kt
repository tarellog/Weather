package com.example.weatherapp.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.model.custommodel.DailyWeatherModel

class DailyWeatherAdapter : RecyclerView.Adapter<DailyWeatherHolder>() {

    private val list: MutableList<DailyWeatherModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DailyWeatherHolder(parent)

    override fun onBindViewHolder(holder: DailyWeatherHolder, position: Int) {
        holder.populate(list[position])
    }

    override fun getItemCount() = list.size

    fun setData(getList: List<DailyWeatherModel>) {
        list.addAll(getList)
        notifyDataSetChanged()
    }

}