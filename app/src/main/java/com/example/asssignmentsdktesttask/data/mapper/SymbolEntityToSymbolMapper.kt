package com.example.asssignmentsdktesttask.data.mapper

import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import com.example.asssignmentsdktesttask.domain.model.Mapper
import com.example.asssignmentsdktesttask.domain.model.Symbol
import javax.inject.Inject

class SymbolEntityToSymbolMapper @Inject constructor() :
    Mapper<SymbolEntity, Symbol> {
    override suspend fun map(from: SymbolEntity): Symbol {
        return Symbol(
            symbol = from.symbol,
            fullName = from.fullName
        )
    }
}