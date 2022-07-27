package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentDailyWeatherBinding
import com.example.weatherapp.ui.DailyWeatherViewModel.ViewState
import com.example.weatherapp.ui.recycler.DailyWeatherAdapter

class DailyWeatherFragment : Fragment() {

    private var _binding: FragmentDailyWeatherBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private lateinit var adapter: DailyWeatherAdapter

    private val viewModel: DailyWeatherViewModel by activityViewModels()

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

        viewModel.basedModel.observe(viewLifecycleOwner, ::render)

    }

    private fun render(state: ViewState) {
        when(state) {
            is ViewState.Success -> showWeather(state)
            is ViewState.Error -> Toast.makeText(
                requireContext(),
                "Произошла ошибка, повторите попытку",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showWeather(viewState: ViewState.Success) {
        adapter.setData(viewState.weather.weatherList)
        binding.city.text = viewState.weather.cityName
    }

}