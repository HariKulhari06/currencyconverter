package com.example.asssignmentsdktesttask.data.mapper

import com.example.asssignmentsdktesttask.data.db.entity.RateEntity
import com.example.asssignmentsdktesttask.data.network.response.Rate
import com.example.asssignmentsdktesttask.domain.model.Mapper
import javax.inject.Inject

class RateResponseToRateEntityRateMapper @Inject constructor() :
    Mapper<Rate, RateEntity> {
    override suspend fun map(from: Rate): RateEntity {
        return RateEntity(
            symbol = from.symbol,
            timeStamp = from.timestamp,
            date = from.date,
            rate = from.rate
        )
    }
}