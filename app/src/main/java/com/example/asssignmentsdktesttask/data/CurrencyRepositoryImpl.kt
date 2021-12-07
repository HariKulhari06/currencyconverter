package com.example.asssignmentsdktesttask.data

import com.example.asssignmentsdktesttask.data.db.database.CurrencyDatabase
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import com.example.asssignmentsdktesttask.data.network.service.FixerCurrencyService
import com.example.asssignmentsdktesttask.domain.model.LoadState
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import networkBoundResource
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyDatabase: CurrencyDatabase,
    private val fixerCurrencyService: FixerCurrencyService
) : CurrencyRepository {
    override fun getCurrencySymbol(): Flow<LoadState<List<Symbol>>> {
        return networkBoundResource(
            query = { currencyDatabase.getSymbols() },
            fetch = { fixerCurrencyService.getSupportedSymbols().first() },
            saveFetchResult = { items: SupportedSymbolsResponse ->
                currencyDatabase.saveSymbols(
                    items
                )
            },
            shouldFetch = { it.isNullOrEmpty() }
        )
    }

    override fun searchSymbols(query: String): Flow<List<Symbol>> {
        return currencyDatabase.searchSymbols(query)
    }
}