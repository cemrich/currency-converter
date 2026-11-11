package de.christinecoenen.code.currencyconverer.ui

import android.icu.util.Currency
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.christinecoenen.code.currencyconverer.R
import de.christinecoenen.code.currencyconverer.ui.theme.CurrencyConvererTheme

@Composable
fun MainScreen(
	modifier: Modifier = Modifier,
) {
	// TODO: use viewmodel
	var sourceAmountText by rememberSaveable { mutableStateOf("") }
	var targetAmountText by rememberSaveable { mutableStateOf("") }
	var sourceCurrency by rememberSaveable { mutableStateOf(Currency.getInstance("EUR")) }
	var targetCurrency by rememberSaveable { mutableStateOf(Currency.getInstance("USD")) }

	MainScreenContent(
		sourceAmount = sourceAmountText,
		targetAmount = targetAmountText,
		sourceCurrency = sourceCurrency,
		targetCurrency = targetCurrency,
		onSourceAmountChanged = {
			sourceAmountText = it
			// TODO: calculate
			targetAmountText = it
		},
		onSwapCurrencies = {
			val swap = sourceCurrency
			sourceCurrency = targetCurrency
			targetCurrency = swap
		},
		modifier = modifier
	)
}

@Composable
fun MainScreenContent(
	sourceAmount: String,
	targetAmount: String,
	sourceCurrency: Currency,
	targetCurrency: Currency,
	modifier: Modifier = Modifier,
	onSourceAmountChanged: (String) -> Unit = {},
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
			OutlinedTextField(
				value = sourceAmount,
				onValueChange = onSourceAmountChanged,
				singleLine = true,
				label = { Text(stringResource(R.string.label_amount)) },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
				modifier = Modifier.fillMaxWidth()
			)

			Row(
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				modifier = Modifier.fillMaxWidth()
			) {
				Button(
					onClick = {},
					Modifier.weight(1f),
				) {
					Text(sourceCurrency.displayName)
				}

				OutlinedIconButton(
					onClick = onSwapCurrencies,
					Modifier.weight(0.5f),
				) {
					Image(
						painter = painterResource(R.drawable.baseline_swap_horiz_24),
						contentDescription = stringResource(R.string.label_swap)
					)
				}

				Button(
					onClick = {},
					Modifier.weight(1f),
				) {
					Text(targetCurrency.displayName)
				}
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
					text = targetAmount,
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.displayMedium,
					modifier = Modifier.fillMaxWidth()
				)
			}
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
			sourceCurrency = Currency.getInstance("EUR"),
			targetCurrency = Currency.getInstance("USD"),
		)
	}
}
