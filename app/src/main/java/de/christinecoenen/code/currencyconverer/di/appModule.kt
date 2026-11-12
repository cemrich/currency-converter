package de.christinecoenen.code.currencyconverer.di

import com.google.gson.GsonBuilder
import de.christinecoenen.code.currencyconverer.api.ExchangeRateResponse
import de.christinecoenen.code.currencyconverer.api.ExchangeRateResponseDeserializer
import de.christinecoenen.code.currencyconverer.api.IExchangeApi
import de.christinecoenen.code.currencyconverer.services.CurrencyService
import de.christinecoenen.code.currencyconverer.services.IExchangeRateCache
import de.christinecoenen.code.currencyconverer.services.ICurrencyService
import de.christinecoenen.code.currencyconverer.services.InMemoryExchangeRateCache
import de.christinecoenen.code.currencyconverer.ui.MainScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
	// TODO: move retrofit setup to helper
	val gson = GsonBuilder()
		.registerTypeAdapter(
			ExchangeRateResponse::class.java,
			ExchangeRateResponseDeserializer ()
		)
		.create()

	val retrofit = Retrofit.Builder()
		// TODO: move URL to config
		// TODO: add logging
		// TODO: add fallback mechanism as described in https://github.com/fawazahmed0/exchange-api
		.baseUrl("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/")
		.addConverterFactory(GsonConverterFactory.create(gson))
		.build()

	single<IExchangeApi> { retrofit.create(IExchangeApi::class.java) }
	single<IExchangeRateCache> { InMemoryExchangeRateCache() }
	single<ICurrencyService> { CurrencyService(get(), get()) }

	viewModel { MainScreenViewModel(get()) }
}
