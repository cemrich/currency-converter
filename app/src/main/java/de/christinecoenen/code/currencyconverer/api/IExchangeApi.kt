package de.christinecoenen.code.currencyconverer.api

import retrofit2.http.GET

interface IExchangeApi {

	@GET("v1/currencies.min.json")
	suspend fun getCurrencies(): Map<String, String>

}
