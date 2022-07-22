package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.weatherapp.databinding.FragmentDailyWeatherBinding
import com.example.weatherapp.ui.recycler.DailyWeatherAdapter

class DailyWeatherFragment : Fragment() {

    private var _binding: FragmentDailyWeatherBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private lateinit var adapter: DailyWeatherAdapter

    private val viewModel: DailyWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyWeatherBinding.inflate(inflater, container, false)

        binding.search.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment()
            val manager = parentFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            searchDialogFragment.show(transaction, "searchDialog")
        }

        adapter = DailyWeatherAdapter()
        binding.recycler.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.todayWeatherModel.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.dailyWeatherModel.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.loadTodayWeatherData()
        viewModel.loadDailyWeatherData()

    }

}