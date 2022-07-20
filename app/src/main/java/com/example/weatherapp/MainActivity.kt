package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.example.weatherapp.data.repository.RemoteRepositoryImpl
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.domain.RemoteRepository

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private val remoteRepository: RemoteRepository = RemoteRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.search.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment()
            val manager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            searchDialogFragment.show(transaction, "searchDialog")
        }
    }

    override fun onStart() {
        super.onStart()
        remoteRepository.requestRepository()
            .subscribe({ Log.d("data", it.toString())}){}
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}