package com.example.asssignmentsdktesttask.model.repository

import com.example.asssignmentsdktesttask.model.LoadState
import com.example.asssignmentsdktesttask.model.Symbol
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencySymbol(): Flow<LoadState<List<Symbol>>>
}