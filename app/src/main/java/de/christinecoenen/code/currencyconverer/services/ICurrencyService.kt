package de.christinecoenen.code.currencyconverer.services

import android.icu.util.Currency
import java.math.BigDecimal

interface ICurrencyService {

	suspend fun getAvailableCurrencies(): List<Currency>

	/**
	 * Converts the given amount from the source currency to the target currency.
	 */
	suspend fun convert(
		sourceCurrencyCode: String,
		amount: BigDecimal,
		targetCurrencyCode: String,
	): BigDecimal
}
