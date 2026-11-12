package de.christinecoenen.code.currencyconverer.ui

import android.icu.util.Currency
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.christinecoenen.code.currencyconverer.ui.theme.CurrencyConvererTheme

@Composable
fun CurrencySelectionButton(
	availableCurrencies: List<Currency>,
	selectedCurrency: Currency,
	modifier: Modifier = Modifier,
	onCurrencySelected: (Currency) -> Unit = {},
) {
	var showSelectCurrencyDialog by rememberSaveable { mutableStateOf(false) }

	Button(
		onClick = { showSelectCurrencyDialog = true },
		modifier
	) {
		// TODO: display displayName without ugly line wraps
		Text(selectedCurrency.currencyCode)
	}

	if (showSelectCurrencyDialog) {
		CurrencySelectionDialog(
			availableCurrencies = availableCurrencies,
			selectedCurrency = selectedCurrency,
			onCurrencySelected = {
				showSelectCurrencyDialog = false
				onCurrencySelected(it)
			},
			onDismissRequest = { showSelectCurrencyDialog = false }
		)
	}
}

@Preview(showBackground = true)
@Composable
fun CurrencySelectionButtonPreview() {
	CurrencyConvererTheme {
		val currencies = Currency.getAvailableCurrencies().toList()

		CurrencySelectionButton(
			availableCurrencies = currencies,
			selectedCurrency = currencies.first(),
		)
	}
}
