package de.christinecoenen.code.currencyconverer.api

import retrofit2.http.GET
import retrofit2.http.Path

interface IExchangeApi {

	@GET("v1/currencies.min.json")
	suspend fun getCurrencies(): Map<String, String>

	@GET("v1/currencies/{currencyCode}.min.json")
	suspend fun getExchangeRates(@Path("currencyCode") currencyCode: String): ExchangeRateResponse
}
