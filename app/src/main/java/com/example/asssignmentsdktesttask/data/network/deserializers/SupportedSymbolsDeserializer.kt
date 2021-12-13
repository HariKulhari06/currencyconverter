package com.example.asssignmentsdktesttask.data.network.deserializers

import com.example.asssignmentsdktesttask.data.network.exception.MaxRequestReachedException
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import com.example.asssignmentsdktesttask.data.network.response.Symbol
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import javax.inject.Inject


class SupportedSymbolsDeserializer @Inject constructor() :
    JsonDeserializer<SupportedSymbolsResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SupportedSymbolsResponse {
        val jsonObject = json.asJsonObject

        if (jsonObject.get("success").asBoolean) {
            val symbols = mutableListOf<Symbol>()
            val symbolJsonObject = jsonObject.get("symbols").asJsonObject

            symbolJsonObject.keySet().forEach { key ->
                symbols.add(
                    Symbol(
                        symbol = key,
                        fullName = symbolJsonObject.get(key).asString
                    )
                )
            }

            return SupportedSymbolsResponse(true, symbols)
        } else {
            throw MaxRequestReachedException()
        }
    }
}