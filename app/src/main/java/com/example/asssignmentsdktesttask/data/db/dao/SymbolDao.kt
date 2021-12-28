package com.example.asssignmentsdktesttask.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import com.example.asssignmentsdktesttask.data.db.relatations.CurrencyWithRate
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SymbolDao : BaseDao<SymbolEntity> {

    @Transaction
    @Query("select * from symbol where is_selected = 1")
    abstract fun getSelectedSymbolWithRate(): Flow<List<CurrencyWithRate>>

    @Query("select * from symbol")
    abstract fun getSymbols(): Flow<List<SymbolEntity>>

    @Query("select * from symbol where symbol like '%' || :query || '%' or full_name like '%' || :query || '%'")
    abstract fun search(query: String): Flow<List<SymbolEntity>>

    @Query("select * from symbol where symbol =:symbol")
    abstract fun getSymbol(symbol: String): SymbolEntity
}