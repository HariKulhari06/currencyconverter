package com.example.asssignmentsdktesttask.domain.repository

import com.example.asssignmentsdktesttask.domain.model.LoadState
import com.example.asssignmentsdktesttask.domain.model.Symbol
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencySymbol(): Flow<LoadState<List<Symbol>>>
    fun searchSymbols(query: String): Flow<List<Symbol>>
}