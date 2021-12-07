package com.example.asssignmentsdktesttask.domain.usecase

import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchSymbolUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(query: String): Flow<List<Symbol>> {
        return currencyRepository.searchSymbols(query)
    }
}