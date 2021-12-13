package com.example.asssignmentsdktesttask.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asssignmentsdktesttask.domain.usecase.CurrencyConverterUseCases
import com.example.asssignmentsdktesttask.utils.ext.toAppError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val useCases: CurrencyConverterUseCases,
) : ViewModel() {
    private val _amount = MutableStateFlow(1000.0)

    private val _baseCurrency = _amount.flatMapLatest { useCases.getBaseCurrencyUseCase() }

    private val _currencies = useCases.getSelectedCurrencyUseCase()

    private val _uiState = combine(
        flow = _baseCurrency,
        flow2 = _currencies,
        flow3 = _amount
    ) { baseCurrency, currencies, amount ->
        UiState(
            isLoading = baseCurrency.isLoading,
            error = baseCurrency.getErrorIfExists().toAppError(),
            amount = amount,
            baseCurrency = baseCurrency.getValueOrNull(),
            currencies = currencies
        )
    }
    val uiState = _uiState

    fun convertCurrency(amount: String = _amount.value.toString()) {
        viewModelScope.launch {
            _amount.value = amount.toDouble()
            useCases
                .convertCurrencyUseCase(amount)
                .flowOn(Dispatchers.IO)
                .collect { state ->

                }
        }
    }

    fun retry() {
        _amount.value = _amount.value
    }
}
