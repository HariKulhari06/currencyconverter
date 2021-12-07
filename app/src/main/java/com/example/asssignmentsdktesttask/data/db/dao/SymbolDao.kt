package com.example.asssignmentsdktesttask.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SymbolDao : BaseDao<SymbolEntity> {

    @Query("select * from symbol")
    abstract fun getSymbols(): Flow<List<SymbolEntity>>

    @Query("select * from symbol where symbol like :query or full_name like :query")
    abstract fun search(query: String): Flow<List<SymbolEntity>>
}