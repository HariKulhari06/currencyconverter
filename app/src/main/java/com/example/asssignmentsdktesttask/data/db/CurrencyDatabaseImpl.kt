package com.example.asssignmentsdktesttask.data.db

import com.example.asssignmentsdktesttask.data.db.dao.RateDao
import com.example.asssignmentsdktesttask.data.db.dao.SymbolDao
import com.example.asssignmentsdktesttask.data.db.database.CurrencyDatabase
import com.example.asssignmentsdktesttask.data.mapper.*
import com.example.asssignmentsdktesttask.data.network.response.Rate
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import com.example.asssignmentsdktesttask.domain.model.Currency
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.utils.ext.forLists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyDatabaseImpl @Inject constructor(
    private val symbolDao: SymbolDao,
    private val rateDao: RateDao,
    private val symbolEntityMapper: SupportedSymbolsResponseToSymbolEntityMapper,
    private val symbolMapper: SymbolEntityToSymbolMapper,
    private val symbolToSymbolEntityMapper: SymbolToSymbolEntityMapper,
    private val currencyMapper: CurrencyWithRateToCurrencyMapper,
    private val rateEntityMapper: RateResponseToRateEntityRateMapper,
) : CurrencyDatabase {
    override suspend fun saveSymbols(item: SupportedSymbolsResponse) {
        symbolDao.insertAll(symbolEntityMapper.forLists().invoke(item.symbols))
    }

    override fun getSymbols(): Flow<List<Symbol>> {
        return symbolDao.getSymbols().map { symbolMapper.forLists().invoke(it) }
    }

    override fun searchSymbols(query: String): Flow<List<Symbol>> {
        return symbolDao.search(query).map { symbolMapper.forLists().invoke(it) }
    }

    override suspend fun updateSymbol(symbol: Symbol) {
        symbolDao.update(symbolToSymbolEntityMapper.map(symbol))
    }

    override suspend fun getSymbol(symbol: String): Symbol {
        return symbolMapper.map(symbolDao.getSymbol(symbol))
    }

    override fun getSelectedCurrencyWithRate(): Flow<List<Currency>> {
        return symbolDao.getSelectedSymbolWithRate().map { currencyMapper.forLists().invoke(it) }
    }

    override suspend fun insertRates(rates: List<Rate>) {
        rateDao.insertAll(rateEntityMapper.forLists().invoke(rates))
    }
}