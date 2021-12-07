package com.example.asssignmentsdktesttask.data.db.database

import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import com.example.asssignmentsdktesttask.domain.model.Symbol
import kotlinx.coroutines.flow.Flow

interface CurrencyDatabase {

    suspend fun saveSymbols(item: SupportedSymbolsResponse)

    fun getSymbols(): Flow<List<Symbol>>

    fun searchSymbols(query: String): Flow<List<Symbol>>

}