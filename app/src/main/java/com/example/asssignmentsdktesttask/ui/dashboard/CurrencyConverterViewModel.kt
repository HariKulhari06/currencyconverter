package com.example.asssignmentsdktesttask.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.asssignmentsdktesttask.model.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    val symbols = currencyRepository.getCurrencySymbol()

}
