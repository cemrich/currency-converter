package de.christinecoenen.code.currencyconverer.di

import de.christinecoenen.code.currencyconverer.api.IExchangeApi
import de.christinecoenen.code.currencyconverer.api.MockExchangeApi
import de.christinecoenen.code.currencyconverer.services.CurrencyService
import de.christinecoenen.code.currencyconverer.services.ICurrencyService
import de.christinecoenen.code.currencyconverer.ui.MainScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
	single<IExchangeApi> { MockExchangeApi() }
	single<ICurrencyService> { CurrencyService(get()) }

	viewModel { MainScreenViewModel(get()) }
}
