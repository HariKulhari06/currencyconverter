package com.example.asssignmentsdktesttask.data.db.relatations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.asssignmentsdktesttask.data.db.entity.RateEntity
import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity

data class CurrencyWithRate(
    @Embedded val symbol: SymbolEntity,
    @Relation(parentColumn = "symbol", entityColumn = "symbol")
    val rate: RateEntity?
)
