package de.christinecoenen.code.currencyconverer.ui

import android.icu.util.Currency
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel : ViewModel() {

	private val _sourceAmountText = MutableStateFlow("")
	val sourceAmountText = _sourceAmountText.asStateFlow()

	private val _targetAmountText = MutableStateFlow("")
	val targetAmountText = _targetAmountText.asStateFlow()

	private val _sourceCurrency = MutableStateFlow(Currency.getInstance("EUR"))
	val sourceCurrency = _sourceCurrency.asStateFlow()

	private val _targetCurrency = MutableStateFlow(Currency.getInstance("USD"))
	val targetCurrency = _targetCurrency.asStateFlow()

	fun onSourceAmountChanged(amount: String) {
		// TODO: validate amount
		// TODO: calculate
		_sourceAmountText.value = amount
		_targetAmountText.value = amount
	}

	fun swapCurrencies() {
		val swap = _sourceCurrency.value
		_sourceCurrency.value = targetCurrency.value
		_targetCurrency.value = swap
	}
}
