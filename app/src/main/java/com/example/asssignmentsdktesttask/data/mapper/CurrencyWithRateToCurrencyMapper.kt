package com.example.asssignmentsdktesttask.data.mapper

import com.example.asssignmentsdktesttask.data.db.relatations.CurrencyWithRate
import com.example.asssignmentsdktesttask.domain.model.Currency
import com.example.asssignmentsdktesttask.domain.model.Mapper
import javax.inject.Inject

class CurrencyWithRateToCurrencyMapper @Inject constructor(
    private val symbolMapper: SymbolEntityToSymbolMapper,
    private val rateMapper: RateEntityToRateMapper
) : Mapper<CurrencyWithRate, Currency> {
    override suspend fun map(from: CurrencyWithRate): Currency {
        return Currency(
            symbol = symbolMapper.map(from.symbol),
            rate = from.rate?.let { rateMapper.map(it) }
        )
    }
}