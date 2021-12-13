package com.example.asssignmentsdktesttask.domain.repository

import com.example.asssignmentsdktesttask.domain.model.Currency
import com.example.asssignmentsdktesttask.domain.model.LoadState
import com.example.asssignmentsdktesttask.domain.model.Symbol
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getBaseCurrency(): Flow<LoadState<Symbol>>

    fun getCurrencySymbol(): Flow<LoadState<List<Symbol>>>

    fun searchSymbols(query: String): Flow<List<Symbol>>

    suspend fun updateSymbol(symbol: Symbol)

    suspend fun getSymbol(symbol: String): Symbol

    fun getSelectedCurrency(): Flow<List<Currency>>

    suspend fun convertCurrency(amount: String): Flow<Unit>
}