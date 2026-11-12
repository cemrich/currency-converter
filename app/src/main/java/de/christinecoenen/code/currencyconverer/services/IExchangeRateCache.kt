package de.christinecoenen.code.currencyconverer.services

import java.math.BigDecimal

// TODO: add dates to help with invalidation
interface IExchangeRateCache {
	fun put(sourceCurrencyCode: String, targetCurrencyCode: String, exchangeRate: BigDecimal)
	fun get(sourceCurrencyCode: String, targetCurrencyCode: String): BigDecimal?
}
