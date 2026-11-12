package de.christinecoenen.code.currencyconverer.services

import android.icu.util.Currency
import de.christinecoenen.code.currencyconverer.api.IExchangeApi
import java.math.BigDecimal

class CurrencyService(
	private val exchangeApi: IExchangeApi,
	private val exchangeRateCache: IExchangeRateCache,
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
		var exchangeRate = exchangeRateCache.get(sourceCurrencyCode, targetCurrencyCode)

		if (exchangeRate == null) {
			// TODO: handle api and network errors
			// TODO: throw dedicated service error objects
			val exchangeRates = exchangeApi
				.getExchangeRates(sourceCurrencyCode.lowercase())
				.conversions

			exchangeRate = exchangeRates[targetCurrencyCode.lowercase()]
				?: throw Error("Target exchange rate $targetCurrencyCode not found in api result.")

			exchangeRateCache.put(sourceCurrencyCode, targetCurrencyCode, exchangeRate)
		}

		return amount.multiply(exchangeRate)
	}
}
