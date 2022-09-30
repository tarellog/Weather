package com.example.weather.dialogweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.weather.R
import com.example.weather.databinding.FragmentSearchDialogBinding

class SearchDialogFragment : DialogFragment() {

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

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

        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_corner_dialog)
        binding.buttonAdd.setOnClickListener {
            val result = binding.editText.text.toString()
            setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to result))
            dismiss()
        }
    }
    companion object {
        const val REQUEST_KEY = "requestKey"
        const val BUNDLE_KEY = "bundleKey"
    }
}