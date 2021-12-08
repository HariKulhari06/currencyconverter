package com.example.asssignmentsdktesttask.data

import com.example.asssignmentsdktesttask.data.db.database.CurrencyDatabase
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import com.example.asssignmentsdktesttask.data.network.service.FixerCurrencyService
import com.example.asssignmentsdktesttask.domain.model.Currency
import com.example.asssignmentsdktesttask.domain.model.LoadState
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import networkBoundResource
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyDatabase: CurrencyDatabase,
    private val fixerCurrencyService: FixerCurrencyService
) : CurrencyRepository {

    override fun getBaseCurrency(): Flow<LoadState<Symbol>> {
        return getCurrencySymbol().map { state ->
            when (state) {
                is LoadState.Error -> LoadState.Error(state.getErrorIfExists()!!)
                is LoadState.Loaded -> LoadState.Loaded(
                    state.getValueOrNull()?.first { it.symbol == BASE_CURRENCY }!!
                )
                LoadState.Loading -> LoadState.Loading
            }
        }
    }

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

    override suspend fun updateSymbol(symbol: Symbol) {
        currencyDatabase.updateSymbol(symbol)
    }

    override suspend fun getSymbol(symbol: String): Symbol {
        return currencyDatabase.getSymbol(symbol)
    }

    override fun getSelectedCurrency(): Flow<List<Currency>> {
        return currencyDatabase.getSelectedCurrencyWithRate()
    }

    override fun convertCurrency(amount: String): Flow<Unit> {
        return getSelectedCurrency()
            .flatMapLatest { selectedCurrencies ->
                fixerCurrencyService.getLatest(selectedCurrencies.joinToString(separator = ",") { it.symbol.symbol })
            }
            .map { response ->
                currencyDatabase.insertRates(response.rates)
            }
    }

    companion object {
        private const val BASE_CURRENCY = "EUR"
    }
}