package com.example.weatherapp.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.common.di.ViewModelFactory
import com.example.weatherapp.databinding.FragmentDailyWeatherBinding
import com.example.weatherapp.dialogweather.SearchDialogFragment
import com.example.weatherapp.weather.adapter.dailyweather.DailyItem
import com.example.weatherapp.weather.adapter.dailyweather.HeaderItem
import com.example.weatherapp.weather.viewmodel.DailyWeatherViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

class DailyWeatherFragment : Fragment() {
    private var _binding: FragmentDailyWeatherBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private val headerAdapter = ItemAdapter<HeaderItem>()
    private val itemAdapter = GenericItemAdapter()
    private val fastAdapter = FastAdapter.with(listOf(headerAdapter, itemAdapter))

    private val viewModel: DailyWeatherViewModel by activityViewModels { ViewModelFactory() }

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

        binding.recycler.adapter = fastAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.error.collect {
                Toast.makeText(
                    requireContext(),
                    "Произошла ошибка, повторите попытку",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.headerModel.collect {
                FastAdapterDiffUtil[headerAdapter] = it.map(::HeaderItem)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.dailyModel.collect {
                FastAdapterDiffUtil[itemAdapter] = it.map(::DailyItem)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.city.collect {
                binding.city.text = it

            }
        }
    }
}