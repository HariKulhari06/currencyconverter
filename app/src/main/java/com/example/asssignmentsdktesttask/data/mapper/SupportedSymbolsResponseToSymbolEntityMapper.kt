package com.example.asssignmentsdktesttask.data.mapper

import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import com.example.asssignmentsdktesttask.data.network.response.Symbol
import com.example.asssignmentsdktesttask.domain.model.Mapper
import javax.inject.Inject

class SupportedSymbolsResponseToSymbolEntityMapper @Inject constructor() :
    Mapper<Symbol, SymbolEntity> {
    override suspend fun map(from: Symbol): SymbolEntity {
        return SymbolEntity(
            symbol = from.symbol,
            fullName = from.fullName
        )
    }
}