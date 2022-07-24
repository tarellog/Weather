package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentSearchDialogBinding

class SearchDialogFragment : DialogFragment() {

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private val viewModel: DailyWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            viewModel.loadBasedWeatherData(binding.editText.text.toString())
            dismiss()
        }

    }

}