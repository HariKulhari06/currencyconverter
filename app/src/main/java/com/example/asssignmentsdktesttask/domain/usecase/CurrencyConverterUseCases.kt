package com.example.asssignmentsdktesttask.domain.usecase

data class CurrencyConverterUseCases(
    val getSymbolUseCase: GetSymbolUseCase,
    val getBaseCurrencyUseCase: GetBaseCurrencyUseCase,
    val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    val convertCurrencyUseCase: ConvertCurrencyUseCase,
)
