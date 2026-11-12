package de.christinecoenen.code.currencyconverer.api

import java.math.BigDecimal
import java.util.Currency

/**
 * Static implementations (without network access) for testing purposes.
 */
class MockExchangeApi : IExchangeApi {

	override suspend fun getCurrencies(): Map<String, String> {
		return Currency
			.getAvailableCurrencies()
			.toList()
			.associate { it.currencyCode to it.displayName }
	}

	override suspend fun getExchangeRates(currencyCode: String): ExchangeRateResponse {
		val exchangeRates = getCurrencies()
			.map { (key) -> key.lowercase() to BigDecimal("2") }
			.toMap()

		return ExchangeRateResponse(
			date = "2026-11-11",
			sourceCurrency = currencyCode.lowercase(),
			conversions = exchangeRates
		)
	}

}
