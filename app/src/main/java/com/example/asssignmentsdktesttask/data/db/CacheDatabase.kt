package com.example.asssignmentsdktesttask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.asssignmentsdktesttask.data.db.dao.RateDao
import com.example.asssignmentsdktesttask.data.db.dao.SymbolDao
import com.example.asssignmentsdktesttask.data.db.entity.RateEntity
import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity

@Database(
    entities = [
        SymbolEntity::class,
        RateEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun symbolDao(): SymbolDao
    abstract fun rateDao(): RateDao
}