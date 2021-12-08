package com.example.asssignmentsdktesttask.di.modules

import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import com.example.asssignmentsdktesttask.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class CurrencyConvertorModule {

    @Provides
    fun provideProfileUseCases(repository: CurrencyRepository): CurrencyConverterUseCases {
        return CurrencyConverterUseCases(
            getSymbolUseCase = GetSymbolUseCase(repository),
            getBaseCurrencyUseCase = GetBaseCurrencyUseCase(repository),
            getSelectedCurrencyUseCase = GetSelectedCurrencyUseCase(repository),
            convertCurrencyUseCase = ConvertCurrencyUseCase(repository)
        )
    }
}