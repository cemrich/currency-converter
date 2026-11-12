package de.christinecoenen.code.currencyconverer.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.math.BigDecimal


class ExchangeRateResponseDeserializer : JsonDeserializer<ExchangeRateResponse> {
	override fun deserialize(
		json: JsonElement?,
		typeOfT: Type?,
		context: JsonDeserializationContext?
	): ExchangeRateResponse? {
		if (json == null) {
			return null
		}

		val gson = Gson()

		val jsonObject = json.asJsonObject
		val currencyKey = jsonObject.keySet().first { it != "date" }
		val conversionMapJsonObject = jsonObject.get(currencyKey)
		val conversionsMapType = object : TypeToken<Map<String, BigDecimal>>() {}.type

		return ExchangeRateResponse(
			date = jsonObject.get("date").asString,
			sourceCurrency = currencyKey,
			conversions = gson.fromJson(conversionMapJsonObject, conversionsMapType)
		)
	}
}
