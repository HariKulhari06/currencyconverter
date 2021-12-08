package com.example.asssignmentsdktesttask.domain.usecase

import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import javax.inject.Inject

class ToggleSymbolSelectionUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(symbol: String) {
        val currencySymbol = currencyRepository.getSymbol(symbol)
        return currencyRepository.updateSymbol(
            currencySymbol.copy(isSelected = currencySymbol.isSelected.not())
        )
    }
}