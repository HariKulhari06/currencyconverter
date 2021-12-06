package com.example.asssignmentsdktesttask.data.network.response

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName


@Keep
data class LatestPriceResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Rates?,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Int
)

@Keep
data class Rates(
    @SerializedName("EUR")
    val eUR: Double?,
    @SerializedName("GBP")
    val gBP: Double?,
    @SerializedName("JPY")
    val jPY: Double?
)