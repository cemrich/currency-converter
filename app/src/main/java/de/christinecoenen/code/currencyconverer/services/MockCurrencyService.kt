package de.christinecoenen.code.currencyconverer.services

import android.icu.util.Currency
import java.math.BigDecimal

/**
 * Static implementations (without network access) for testing purposes.
 */
class MockCurrencyService : ICurrencyService {
	override suspend fun getAvailableCurrencies(): List<Currency> {
		return Currency.getAvailableCurrencies().toList()
	}

	override suspend fun convert(
		sourceCurrencyCode: String,
		amount: BigDecimal,
		targetCurrencyCode: String
	): BigDecimal {
		return amount.multiply(BigDecimal("2"))
	}
}
