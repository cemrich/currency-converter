package de.christinecoenen.code.currencyconverer.api

interface IExchangeApi {

	suspend fun getCurrencies(): Map<String, String>

}
