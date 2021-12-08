package com.example.asssignmentsdktesttask.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asssignmentsdktesttask.domain.usecase.CurrencyConverterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val useCases: CurrencyConverterUseCases,
) : ViewModel() {
    private val _amount = MutableStateFlow(1000.0)

    private val _baseCurrency = useCases.getBaseCurrencyUseCase()

    private val _currencies = useCases.getSelectedCurrencyUseCase()

    private val _uiState = combine(
        flow = _baseCurrency,
        flow2 = _currencies,
        flow3 = _amount
    ) { baseCurrency, currencies, amount ->

        UiState(
            isLoading = baseCurrency.isLoading,
            error = baseCurrency.getErrorIfExists(),
            amount = amount,
            baseCurrency = baseCurrency.getValueOrNull(),
            currencies = currencies
        )
    }
    val uiState = _uiState

    fun convertCurrency(amount: String) {
        viewModelScope.launch {
            _amount.value = amount.toDouble()
            useCases
                .convertCurrencyUseCase(amount)
                .flowOn(Dispatchers.IO)
                .collect { state ->

                }
        }
    }
}
