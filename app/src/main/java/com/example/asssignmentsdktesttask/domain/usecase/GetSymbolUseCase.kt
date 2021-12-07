package com.example.asssignmentsdktesttask.domain.usecase

import com.example.asssignmentsdktesttask.domain.model.LoadState
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetSymbolUseCase(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(): Flow<LoadState<List<Symbol>>> {
        return currencyRepository.getCurrencySymbol()
    }
}