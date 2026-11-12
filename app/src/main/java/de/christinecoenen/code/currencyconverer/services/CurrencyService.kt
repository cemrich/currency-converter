package de.christinecoenen.code.currencyconverer.services

import android.icu.util.Currency
import de.christinecoenen.code.currencyconverer.api.IExchangeApi
import java.math.BigDecimal

class CurrencyService(
	private val exchangeApi: IExchangeApi,
) : ICurrencyService {

	override suspend fun getAvailableCurrencies(): List<Currency> {
		// TODO: cache result for at least a day to avoid hitting the api too often
		val availableCurrencies = exchangeApi.getCurrencies()

		return Currency
			.getAvailableCurrencies()
			.toList()
			.filter { androidCurrency -> availableCurrencies.containsKey(androidCurrency.currencyCode) }
	}

	override suspend fun convert(
		sourceCurrencyCode: String,
		amount: BigDecimal,
		targetCurrencyCode: String
	): BigDecimal {
		return amount.multiply(BigDecimal("2"))
	}
}
