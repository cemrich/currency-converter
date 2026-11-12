package de.christinecoenen.code.currencyconverer.services

import java.math.BigDecimal

// TODO: unit test
class InMemoryExchangeRateCache : IExchangeRateCache {

	private val exchangeRates = mutableMapOf<String, BigDecimal>()

	override fun put(
		sourceCurrencyCode: String,
		targetCurrencyCode: String,
		exchangeRate: BigDecimal
	) {
		exchangeRates[key(sourceCurrencyCode, targetCurrencyCode)] = exchangeRate
	}

	override fun get(
		sourceCurrencyCode: String,
		targetCurrencyCode: String
	): BigDecimal? = exchangeRates[key(sourceCurrencyCode, targetCurrencyCode)]

	private fun key(
		sourceCurrencyCode: String,
		targetCurrencyCode: String,
	) = "$sourceCurrencyCode-$targetCurrencyCode".lowercase()
}
