package com.example.asssignmentsdktesttask.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.databinding.FragmentCurrencyConverterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyConverterFragment : Fragment(R.layout.fragment_currency_converter) {

    private val viewModel: CurrencyConverterViewModel by viewModels()

    private var _binding: FragmentCurrencyConverterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCurrencyConverterBinding.bind(view)

        lifecycleScope.launch {
            viewModel.symbols.collect {
                print(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}