package com.example.weatherapp.common

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.core.flow.observe
import com.example.core.navigation.NavCommand
import com.example.core.navigation.navigate
import com.example.weatherapp.R
import com.example.weather.R as WeatherR
import com.example.cities.R as CitiesR
import com.example.windowdialog.R as SearchR
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    @Inject
    lateinit var navigationCommand: MutableSharedFlow<NavCommand>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButtonNavigationView()
        navigationCommand.observe(
            lifecycleScope,
            action = { findNavController(R.id.container_fragment).navigate(it) },
            onError = { Log.e("log","error", it) }
        )
    }

    private fun setButtonNavigationView() {
        binding.bottomNav.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.main -> {
                    navigateFragment(WeatherR.id.weather_nav_graph)
                    return@setOnItemSelectedListener true
                }
                R.id.favorite -> {
                    navigateFragment(CitiesR.id.city_nav_graph)
                    return@setOnItemSelectedListener true
                }
                R.id.search -> {
                    navigateFragment(SearchR.id.dialog_nav_graph)
                    return@setOnItemSelectedListener true
                }
                else -> { return@setOnItemSelectedListener false }
            }
        }
    }

    private fun navigateFragment(@IdRes fragment: Int) {
        findNavController(R.id.container_fragment)
            .navigate(fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}