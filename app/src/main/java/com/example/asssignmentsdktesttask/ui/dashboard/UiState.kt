package com.example.asssignmentsdktesttask.ui.dashboard

import com.example.asssignmentsdktesttask.domain.model.Currency
import com.example.asssignmentsdktesttask.domain.model.Symbol

data class UiState(
    val isLoading: Boolean,
    val error: Throwable?,
    val amount: Double?,
    val baseCurrency: Symbol?,
    val currencies: List<Currency>?
) {
    companion object {
        val EMPTY = UiState(
            isLoading = false,
            error = null,
            amount = 1000.0,
            baseCurrency = null,
            currencies = null
        )
    }
}
