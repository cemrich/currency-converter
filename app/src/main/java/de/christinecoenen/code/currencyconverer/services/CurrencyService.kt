package de.christinecoenen.code.currencyconverer.services

import android.icu.util.Currency
import de.christinecoenen.code.currencyconverer.api.IExchangeApi
import java.math.BigDecimal

class CurrencyService(
	private val exchangeApi: IExchangeApi,
) : ICurrencyService {

	// TODO: unit test
	override suspend fun getAvailableCurrencies(): List<Currency> {
		// TODO: handle api and network errors
		// TODO: cache result for at least a day to avoid hitting the api too often
		val availableCurrencyKeys = exchangeApi.getCurrencies().map { it.key.lowercase() }

		// intersect available currencies on our android system with the ones available through the api
		return Currency
			.getAvailableCurrencies()
			.toList()
			.filter { androidCurrency -> availableCurrencyKeys.contains(androidCurrency.currencyCode.lowercase()) }
	}

	// TODO: unit test
	override suspend fun convert(
		sourceCurrencyCode: String,
		amount: BigDecimal,
		targetCurrencyCode: String
	): BigDecimal {
		// TODO: cache result for at least a day to avoid hitting the api too often
		// TODO: handle api and network errors
		// TODO: throw dedicated service error objects
		val exchangeRates = exchangeApi
			.getExchangeRates(sourceCurrencyCode.lowercase())
			.conversions

		val exchangeRate = exchangeRates[targetCurrencyCode.lowercase()]
			?: throw Error("Target exchange rate $targetCurrencyCode not found in api result.")

		return amount.multiply(exchangeRate)
	}
}
