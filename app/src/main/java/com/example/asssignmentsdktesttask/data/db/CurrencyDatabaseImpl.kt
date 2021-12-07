package com.example.asssignmentsdktesttask.data.db

import com.example.asssignmentsdktesttask.data.db.dao.SymbolDao
import com.example.asssignmentsdktesttask.data.db.database.CurrencyDatabase
import com.example.asssignmentsdktesttask.data.mapper.SupportedSymbolsResponseToSymbolEntityMapper
import com.example.asssignmentsdktesttask.data.mapper.SymbolEntityToSymbolMapper
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.utils.ext.forLists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyDatabaseImpl @Inject constructor(
    private val symbolDao: SymbolDao,
    private val symbolEntityMapper: SupportedSymbolsResponseToSymbolEntityMapper,
    private val symbolMapper: SymbolEntityToSymbolMapper,

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
}