package com.example.asssignmentsdktesttask.data.network.response

import androidx.annotation.Keep
import com.example.asssignmentsdktesttask.data.network.deserializers.SupportedSymbolsDeserializer
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName


@Keep
@JsonAdapter(SupportedSymbolsDeserializer::class)
data class SupportedSymbolsResponse(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("symbols")
    val symbols: List<Symbol>
)

@Keep
data class Symbol(
    val symbol: String,
    val fullName: String
)
