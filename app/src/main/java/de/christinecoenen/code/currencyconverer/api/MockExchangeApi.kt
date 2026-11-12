package de.christinecoenen.code.currencyconverer.api

import java.util.Currency

/**
 * Static implementations (without network access) for testing purposes.
 */
class MockExchangeApi : IExchangeApi {

	override suspend fun getCurrencies(): Map<String, String> {
		return Currency
			.getAvailableCurrencies()
			.toList()
			.take(10)
			.associate { it.currencyCode to it.displayName }
	}

}
