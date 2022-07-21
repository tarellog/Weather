package com.example.weatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.databinding.DailyItemBinding

class DailyWeatherHolder
    (
    private val binding: DailyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        DailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate(item: ListWeatherModel) {

    }
}