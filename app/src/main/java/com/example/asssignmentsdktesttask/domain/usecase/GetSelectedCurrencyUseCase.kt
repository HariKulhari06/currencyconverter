package com.example.asssignmentsdktesttask.domain.usecase

import com.example.asssignmentsdktesttask.domain.model.Currency
import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(): Flow<List<Currency>> {
        return currencyRepository.getSelectedCurrency()
    }
}