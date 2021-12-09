package com.example.asssignmentsdktesttask.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.asssignmentsdktesttask.data.db.entity.RateEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RateDao : BaseDao<RateEntity> {
    @Query("select * from rate")
    abstract fun getRates(): Flow<List<RateEntity>>
}