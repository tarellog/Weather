package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.weatherapp.R
import com.example.weatherapp.data.repository.RemoteRepositoryImpl
import com.example.weatherapp.databinding.FragmentDailyWeatherBinding
import com.example.weatherapp.domain.RemoteRepository

class DailyWeatherFragment : Fragment() {

    var _binding: FragmentDailyWeatherBinding? = null
    val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private val remoteRepository: RemoteRepository = RemoteRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.search.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment()
            val manager = parentFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            searchDialogFragment.show(transaction, "searchDialog")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

}