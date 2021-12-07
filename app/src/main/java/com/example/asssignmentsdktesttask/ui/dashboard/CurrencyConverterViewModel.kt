package com.example.asssignmentsdktesttask.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.asssignmentsdktesttask.domain.usecase.CurrencyConverterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyConverterUseCases: CurrencyConverterUseCases
) : ViewModel()
