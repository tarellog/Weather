package com.example.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.constants.TransmitNameCityByKey
import com.example.core.flow.observe
import com.example.core.viewmodel.ViewModelFactory
import com.example.weather.adapter.WeatherAdapter
import com.example.weather.common.WeatherComponentHolder
import com.example.weather.databinding.FragmentDailyWeatherBinding
import javax.inject.Inject

class DailyWeatherFragment : Fragment() {

    private var _binding: FragmentDailyWeatherBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private val weatherAdapter = WeatherAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DailyWeatherViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        WeatherComponentHolder
            .getComponentImpl()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyWeatherBinding.inflate(inflater, container, false)

        setFragmentResultListener(TransmitNameCityByKey.REQUEST_KEY) { _, bundle ->
            val result = bundle.getString(TransmitNameCityByKey.BUNDLE_KEY)
            viewModel.displayDataWeather(result.toString())
        }

//        binding.search.setOnClickListener { viewModel.navigationToScreenDialog() }

        binding.recycler.adapter = weatherAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherData.observe(
            viewLifecycleOwner.lifecycleScope,
            action = { weatherAdapter.submitList(it) },
            onError = { Log.e("log", "error") }
        )
        viewModel.message.observe(
            viewLifecycleOwner.lifecycleScope,
            action = { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() },
            onError = { Log.e("log", "error") }
        )
    }

}