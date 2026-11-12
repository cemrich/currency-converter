package de.christinecoenen.code.currencyconverer.ui

import android.icu.util.Currency
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.christinecoenen.code.currencyconverer.services.ICurrencyService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainScreenViewModel(
	private val currencyService: ICurrencyService,
) : ViewModel() {

	private val _availableCurrencies = MutableStateFlow(listOf<Currency>())
	val availableCurrencies = _availableCurrencies.asStateFlow()

	private val _sourceAmountText = MutableStateFlow("")
	val sourceAmountText = _sourceAmountText.asStateFlow()

	private val _targetAmountText = MutableStateFlow("")
	val targetAmountText = _targetAmountText.asStateFlow()

	// TODO: make this nullable and initialize with a sane default value once availableCurrencies are loaded
	private val _sourceCurrency = MutableStateFlow(Currency.getInstance("EUR"))
	val sourceCurrency = _sourceCurrency.asStateFlow()

	// TODO: make this nullable and initialize with a sane default value once availableCurrencies are loaded
	private val _targetCurrency = MutableStateFlow(Currency.getInstance("USD"))
	val targetCurrency = _targetCurrency.asStateFlow()

	init {
		viewModelScope.launch {
			// TODO: show loading state in ui as this may take a while
			_availableCurrencies.value = currencyService.getAvailableCurrencies()
		}
	}

	fun onSourceAmountChanged(amount: String) {
		_sourceAmountText.value = amount

		val parsedAmount = textToMoneyAmount(_sourceAmountText.value)

		viewModelScope.launch {
			// TODO: show loading state in ui as this may take a while
			val converted = currencyService.convert(
				sourceCurrencyCode = _sourceCurrency.value.currencyCode,
				targetCurrencyCode = _targetCurrency.value.currencyCode,
				amount = parsedAmount,
			)

			// TODO: proper formatting (locale aware, currency aware decimal places)
			_targetAmountText.value = converted.toString()
		}
	}

	fun onSourceCurrencyChanged(currency: Currency) {
		_sourceCurrency.value = currency
	}

	fun onTargetCurrencyChanged(currency: Currency) {
		_targetCurrency.value = currency
	}

	fun swapCurrencies() {
		val swap = _sourceCurrency.value
		_sourceCurrency.value = targetCurrency.value
		_targetCurrency.value = swap
	}

	private fun textToMoneyAmount(text: String): BigDecimal {
		// TODO: add locale aware decimal point handling
		return if (text.isBlank()) {
			BigDecimal.ZERO
		} else {
			try {
				BigDecimal(text)
			} catch (_: NumberFormatException) {
				// TODO: raise errors to the ui (eg. when typing letters)
				BigDecimal.ZERO
			}
		}
	}
}
