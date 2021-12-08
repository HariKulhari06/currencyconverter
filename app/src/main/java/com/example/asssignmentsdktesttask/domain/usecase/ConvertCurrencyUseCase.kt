package com.example.asssignmentsdktesttask.domain.usecase

import com.example.asssignmentsdktesttask.domain.model.LoadState
import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import com.example.asssignmentsdktesttask.utils.ext.toLoadingState
import kotlinx.coroutines.flow.Flow

class ConvertCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(amount: String): Flow<LoadState<Unit>> {
        return currencyRepository.convertCurrency(amount).toLoadingState()
    }
}