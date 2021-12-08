package com.example.asssignmentsdktesttask.data.network.response

import androidx.annotation.Keep
import com.example.asssignmentsdktesttask.data.network.deserializers.LatestRateResponseDeserializer
import com.google.gson.annotations.JsonAdapter


@JsonAdapter(LatestRateResponseDeserializer::class)
@Keep
data class LatestRateResponse(
    val success: Boolean,
    val rates: List<Rate>
)

@Keep
data class Rate(
    val rate: Double?,
    val symbol: String,
    val date: String,
    val timestamp: Int,
)