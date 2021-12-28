package com.example.asssignmentsdktesttask.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.ui.theme.CurrencyConverterTheme

@ExperimentalMaterial3Api
@Composable
fun CurrencyConverterScaffold(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable (PaddingValues) -> Unit
) {
    CurrencyConverterTheme {
        Scaffold(
            topBar = {
                CurrencyConverterAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                ) {

                }
            },
            content = content
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun CurrencyConverterScaffoldPreview() {
    CurrencyConverterScaffold {}
}