package com.example.asssignmentsdktesttask.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun CurrencyConverterAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier.statusBarsPadding(),
        actions = actions,
        title = title,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            /* IconButton(onClick = onNavIconPressed) {
                 Icon(
                     painter = painterResource(id = R.drawable.ic_round_add_24),
                     contentDescription = "Navigation Icon"
                 )
             }*/
        }
    )

}