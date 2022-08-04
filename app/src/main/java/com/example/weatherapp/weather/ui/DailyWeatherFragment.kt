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
import com.example.weatherapp.weather.viewmodel.DailyWeatherViewModel.ViewState
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

    private val viewModel: DailyWeatherViewModel by activityViewModels{ ViewModelFactory() }

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
            viewModel.empty.collect(::render)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.error.collect(::render)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.dailyModel.collect(::render)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.headerModel.collect(::render)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.city.collect(::render)
        }
    }

    private fun render(state: ViewState) {
        when(state) {
            is ViewState.Empty -> binding.toolbar
            is ViewState.Header -> FastAdapterDiffUtil[headerAdapter] = state.today.map(::HeaderItem)
            is ViewState.Success -> FastAdapterDiffUtil[itemAdapter] = state.daily.map(::DailyItem)
            is ViewState.City -> binding.city.text = state.city
            is ViewState.Error -> Toast.makeText(
                requireContext(),
                "Произошла ошибка, повторите попытку",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}