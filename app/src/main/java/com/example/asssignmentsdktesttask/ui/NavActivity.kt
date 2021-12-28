package com.example.asssignmentsdktesttask.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.WindowCompat
import com.example.asssignmentsdktesttask.databinding.ContentMainBinding
import com.example.asssignmentsdktesttask.ui.components.CurrencyConverterScaffold
import com.example.asssignmentsdktesttask.ui.components.LocalBackPressedDispatcher
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class NavActivity : AppCompatActivity() {
    private val systemViewModel: SystemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ProvideWindowInsets {
                CompositionLocalProvider(LocalBackPressedDispatcher provides this.onBackPressedDispatcher) {
                    val scaffoldState = rememberScaffoldState()
                    CurrencyConverterScaffold(scaffoldState) {
                        AndroidViewBinding(ContentMainBinding::inflate)
                    }
                }
            }
        }
    }

}