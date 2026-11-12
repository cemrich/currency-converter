package de.christinecoenen.code.currencyconverer.api

import java.math.BigDecimal

data class ExchangeRateResponse(
	val date: String,
	val sourceCurrency: String,
	val conversions: Map<String, BigDecimal>,
)
