package com.example.weatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.custommodel.TodayWeatherModel
import com.example.weatherapp.databinding.HeaderItemBinding

class HeaderScreenWeatherHolder(
    private val binding: HeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun populate() {

    }
}