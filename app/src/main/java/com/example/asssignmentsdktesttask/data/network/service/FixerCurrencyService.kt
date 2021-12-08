package com.example.asssignmentsdktesttask.data.network.service

import com.example.asssignmentsdktesttask.data.network.response.LatestRateResponse
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface FixerCurrencyService {
    @GET("symbols")
    fun getSupportedSymbols(): Flow<SupportedSymbolsResponse>

    @GET("latest")
    fun getLatest(@Query("symbols") symbols: String): Flow<LatestRateResponse>
}