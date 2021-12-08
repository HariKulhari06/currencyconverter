package com.example.asssignmentsdktesttask.domain.model


data class Rate(
    val symbol: String,
    val timeStamp: Int,
    val date: String,
    val rate: Double?
) {
    fun getSingleUnitRate(baseCurrency: String) = "1 $baseCurrency = $rate $symbol"

    fun getRateForAmount(amount: Double) = rate?.let { amount.times(it) }.toString()
}
