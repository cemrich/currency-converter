package de.christinecoenen.code.currencyconverer.ui

import android.icu.util.Currency
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import de.christinecoenen.code.currencyconverer.R
import de.christinecoenen.code.currencyconverer.ui.theme.CurrencyConvererTheme

@Composable
fun CurrencySelectionDialog(
	availableCurrencies: List<Currency>,
	selectedCurrency: Currency,
	onCurrencySelected: (Currency) -> Unit = {},
	onDismissRequest: () -> Unit = {},
) {
	Dialog(
		onDismissRequest = onDismissRequest
	) {
		Card(
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(max = 400.dp)
				.padding(16.dp),
			shape = RoundedCornerShape(16.dp),
		) {
			Text(
				text = stringResource(R.string.label_select_currency),
				style = MaterialTheme.typography.headlineMedium,
				modifier = Modifier.padding(16.dp)
			)

			// TODO: add search field

			LazyColumn(Modifier.selectableGroup()) {
				items(availableCurrencies) { currency ->
					val isSelected = currency == selectedCurrency
					Row(
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier
							.fillMaxWidth()
							.height(56.dp)
							.selectable(
								selected = isSelected,
								onClick = { onCurrencySelected(currency) },
								role = Role.RadioButton
							)
							.padding(horizontal = 16.dp)
					) {
						RadioButton(
							selected = isSelected,
							onClick = null // null recommended for accessibility with screen readers
						)
						Text(
							text = currency.displayName,
							style = MaterialTheme.typography.bodyLarge,
							modifier = Modifier.padding(start = 16.dp)
						)
					}
				}
			}
		}
	}
}

@Preview(
	showBackground = true,
	widthDp = 500,
	heightDp = 500,
)
@Composable
fun CurrencySelectionDialogPreview() {
	CurrencyConvererTheme {
		val currencies = Currency.getAvailableCurrencies().toList()

		CurrencySelectionDialog(
			availableCurrencies = currencies,
			selectedCurrency = currencies.first(),
		)
	}
}
