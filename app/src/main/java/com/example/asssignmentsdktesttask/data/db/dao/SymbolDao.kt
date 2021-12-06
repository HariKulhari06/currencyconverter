package com.example.asssignmentsdktesttask.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SymbolDao : BaseDao<SymbolEntity> {

    @Query("select * from symbol")
    abstract fun getSymbols(): Flow<List<SymbolEntity>>
}