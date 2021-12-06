package com.example.asssignmentsdktesttask.data.network.service

import com.example.asssignmentsdktesttask.data.network.response.LatestPriceResponse
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface FixerCurrencyService {
    @GET("symbols")
    fun getSupportedSymbols(): Flow<SupportedSymbolsResponse>

    @GET("latest/{symbols}")
    fun getLatest(): Flow<LatestPriceResponse>
}