package com.example.asssignmentsdktesttask.data.mapper

import com.example.asssignmentsdktesttask.data.db.entity.RateEntity
import com.example.asssignmentsdktesttask.domain.model.Mapper
import com.example.asssignmentsdktesttask.domain.model.Rate
import javax.inject.Inject

class RateEntityToRateMapper @Inject constructor() :
    Mapper<RateEntity, Rate> {
    override suspend fun map(from: RateEntity): Rate {
        return Rate(
            symbol = from.symbol,
            timeStamp = from.timeStamp,
            date = from.date,
            rate = from.rate
        )
    }
}