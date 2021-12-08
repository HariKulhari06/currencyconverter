package com.example.asssignmentsdktesttask.data.mapper

import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import com.example.asssignmentsdktesttask.domain.model.Mapper
import com.example.asssignmentsdktesttask.domain.model.Symbol
import javax.inject.Inject

class SymbolToSymbolEntityMapper @Inject constructor() :
    Mapper<Symbol, SymbolEntity> {
    override suspend fun map(from: Symbol): SymbolEntity {
        return SymbolEntity(
            symbol = from.symbol,
            fullName = from.fullName,
            isSelected = from.isSelected
        )
    }
}