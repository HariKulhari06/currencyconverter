package com.example.asssignmentsdktesttask.data.network.deserializers

import com.example.asssignmentsdktesttask.data.network.exception.MaxRequestReachedException
import com.example.asssignmentsdktesttask.data.network.response.LatestRateResponse
import com.example.asssignmentsdktesttask.data.network.response.Rate
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import javax.inject.Inject


class LatestRateResponseDeserializer @Inject constructor() :
    JsonDeserializer<LatestRateResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LatestRateResponse {
        val jsonObject = json.asJsonObject

        val timestamp = jsonObject.get("timestamp").asLong
        val date = jsonObject.get("date").asString

        if (jsonObject.get("success").asBoolean) {
            val rates = mutableListOf<Rate>()
            val ratesJsonObject = jsonObject.get("rates").asJsonObject

            ratesJsonObject.keySet().forEach { key ->
                rates.add(
                    Rate(
                        symbol = key,
                        rate = ratesJsonObject.get(key).asDouble,
                        timestamp = timestamp,
                        date = date
                    )
                )
            }

            return LatestRateResponse(true, rates)
        } else {
            throw MaxRequestReachedException()
        }
    }
}