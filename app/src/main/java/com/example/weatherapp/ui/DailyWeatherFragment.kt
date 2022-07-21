package com.example.weatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.weatherapp.data.repository.RemoteRepositoryImpl
import com.example.weatherapp.databinding.FragmentDailyWeatherBinding
import com.example.weatherapp.domain.RemoteRepository
import com.example.weatherapp.ui.recycler.DailyWeatherAdapter

class DailyWeatherFragment : Fragment() {

    var _binding: FragmentDailyWeatherBinding? = null
    val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private val remoteRepository: RemoteRepository = RemoteRepositoryImpl()
    private val adapter: DailyWeatherAdapter = DailyWeatherAdapter()

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        loadData()

    }

    private fun loadData() {
        remoteRepository.requestRepository()
            .subscribe({adapter.setData(it.list)}){}
    }

}