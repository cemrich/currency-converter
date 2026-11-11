package de.christinecoenen.code.currencyconverer.di

import de.christinecoenen.code.currencyconverer.ui.MainScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
	viewModel { MainScreenViewModel() }
}
