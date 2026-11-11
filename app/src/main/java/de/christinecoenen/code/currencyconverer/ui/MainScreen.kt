package de.christinecoenen.code.currencyconverer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
	Card(
		modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(16.dp),
			modifier = Modifier.padding(16.dp)
		) {
			var amountText by rememberSaveable { mutableStateOf("") }

			OutlinedTextField(
				value = amountText,
				onValueChange = { amountText = it },
				singleLine = true,
				label = { Text(stringResource(R.string.label_amount)) },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
				modifier = Modifier.fillMaxWidth()
			)

			Row(
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier.fillMaxWidth()
			) {
				Button(
					onClick = {}
				) {
					Text("Currency 1")
				}

				IconButton(
					onClick = {
						// TODO: swap curencies
					}
				) {
					Image(
						painter = painterResource(R.drawable.baseline_swap_horiz_24),
						contentDescription = stringResource(R.string.label_swap)
					)
				}

				Button(
					onClick = {}
				) {
					Text("Currency 2")
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
					text = "45,23 €",
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
		MainScreen()
	}
}
