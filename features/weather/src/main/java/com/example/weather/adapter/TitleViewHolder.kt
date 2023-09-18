package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core.R
import com.example.weather.databinding.TitleItemBinding

class TitleViewHolder(private val binding: TitleItemBinding) : ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        TitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind() {
        binding.title.text = binding.root.context.getString(R.string.title)
    }

}