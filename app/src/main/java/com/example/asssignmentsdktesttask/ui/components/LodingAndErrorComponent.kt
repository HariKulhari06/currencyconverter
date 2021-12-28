package com.example.asssignmentsdktesttask.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.domain.model.AppError
import com.example.asssignmentsdktesttask.utils.ext.stringRes

@Composable
fun LoadingAndErrorComponent(
    isLoading: Boolean,
    error: AppError? = null,
    onRetry: (() -> Unit)? = null
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            }

            if (error != null) {
                Text(
                    text = stringResource(id = error.stringRes()),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onRetry?.invoke() }
                ) {
                    Text(
                        text = stringResource(id = R.string.retry),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }


        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun LoadingPreview() {
    LoadingAndErrorComponent(isLoading = true)
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ErrorPreview() {
    LoadingAndErrorComponent(
        isLoading = false,
        error = AppError.ApiException.NetworkConnectivityError(Throwable())
    )
}