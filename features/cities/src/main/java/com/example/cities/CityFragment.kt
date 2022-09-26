package com.example.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cities.databinding.FragmentCityBinding
import com.example.cities.dimodule.CityComponentHolder

class CityFragment : Fragment() {
    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        CityComponentHolder
            .getComponentImpl()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        binding.customToolbar.back.visibility = View.VISIBLE
        binding.customToolbar.back.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}