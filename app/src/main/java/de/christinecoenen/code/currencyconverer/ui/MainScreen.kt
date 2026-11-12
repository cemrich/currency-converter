package de.christinecoenen.code.currencyconverer.ui

import android.icu.util.Currency
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.christinecoenen.code.currencyconverer.R
import de.christinecoenen.code.currencyconverer.ui.theme.CurrencyConvererTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
	modifier: Modifier = Modifier,
) {
	val viewModel: MainScreenViewModel = koinViewModel()

	val availableCurrencies by viewModel.availableCurrencies.collectAsStateWithLifecycle()
	val sourceAmountText by viewModel.sourceAmountText.collectAsStateWithLifecycle()
	val targetAmountText by viewModel.targetAmountText.collectAsStateWithLifecycle()
	val sourceCurrency by viewModel.sourceCurrency.collectAsStateWithLifecycle()
	val targetCurrency by viewModel.targetCurrency.collectAsStateWithLifecycle()

	MainScreenContent(
		sourceAmount = sourceAmountText,
		targetAmount = targetAmountText,
		availableCurrencies = availableCurrencies,
		sourceCurrency = sourceCurrency,
		targetCurrency = targetCurrency,
		onSourceAmountChanged = viewModel::onSourceAmountChanged,
		onSourceCurrencyChanged = viewModel::onSourceCurrencyChanged,
		onTargetCurrencyChanged = viewModel::onTargetCurrencyChanged,
		onSwapCurrencies = viewModel::swapCurrencies,
		modifier = modifier
	)
}

@Composable
fun MainScreenContent(
	sourceAmount: String,
	targetAmount: String,
	availableCurrencies: List<Currency>,
	sourceCurrency: Currency,
	targetCurrency: Currency,
	modifier: Modifier = Modifier,
	onSourceAmountChanged: (String) -> Unit = {},
	onSourceCurrencyChanged: (Currency) -> Unit = {},
	onTargetCurrencyChanged: (Currency) -> Unit = {},
	onSwapCurrencies: () -> Unit = {},
) {
	Card(
		modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(16.dp),
			modifier = Modifier.padding(16.dp)
		) {
			// TODO: prohibit every input except digits and decimal places via InputTransformation
			// TODO: restrict max length
			// TODO: change to state based text field as described in https://developer.android.com/develop/ui/compose/text/user-input
			OutlinedTextField(
				value = sourceAmount,
				onValueChange = onSourceAmountChanged,
				singleLine = true,
				label = { Text(stringResource(R.string.label_amount)) },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
				modifier = Modifier.fillMaxWidth()
			)

			// TODO: move currency selection into its own composable
			Row(
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				modifier = Modifier.fillMaxWidth()
			) {
				CurrencySelectionButton(
					availableCurrencies = availableCurrencies,
					selectedCurrency = sourceCurrency,
					onCurrencySelected = onSourceCurrencyChanged,
					modifier = Modifier.weight(1f)
				)

				// TODO: wrong icon tint in dark mode ui
				OutlinedIconButton(
					onClick = onSwapCurrencies,
					Modifier.weight(0.5f),
				) {
					Image(
						painter = painterResource(R.drawable.baseline_swap_horiz_24),
						contentDescription = stringResource(R.string.label_swap)
					)
				}

				CurrencySelectionButton(
					availableCurrencies = availableCurrencies,
					selectedCurrency = targetCurrency,
					onCurrencySelected = onTargetCurrencyChanged,
					modifier = Modifier.weight(1f)
				)
			}

			Column(
				verticalArrangement = Arrangement.spacedBy(4.dp),
			) {
				Text(
					text = stringResource(R.string.label_result),
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.labelMedium,
					modifier = Modifier.fillMaxWidth()
				)

				Text(
					text = "$targetAmount ${targetCurrency.symbol}",
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.displayMedium,
					modifier = Modifier.fillMaxWidth()
				)
			}

			// TODO: Display conversion rate, its source and date
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
	CurrencyConvererTheme {
		MainScreenContent(
			sourceAmount = "15",
			targetAmount = "17,34",
			availableCurrencies = Currency.getAvailableCurrencies().toList(),
			sourceCurrency = Currency.getInstance("EUR"),
			targetCurrency = Currency.getInstance("USD"),
		)
	}
}
