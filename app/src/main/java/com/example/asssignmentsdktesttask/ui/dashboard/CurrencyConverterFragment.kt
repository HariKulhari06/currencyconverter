package com.example.asssignmentsdktesttask.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.btnAddCurrency
import com.example.asssignmentsdktesttask.currencyCard
import com.example.asssignmentsdktesttask.databinding.FragmentCurrencyConverterBinding
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.loadingAndErrorState
import com.example.asssignmentsdktesttask.ui.components.LoadingAndErrorComponent
import com.example.asssignmentsdktesttask.ui.dashboard.views.BaseCurrencyModel
import com.example.asssignmentsdktesttask.ui.theme.CurrencyConverterTheme
import com.example.asssignmentsdktesttask.utils.ext.stringRes
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ViewWindowInsetObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterFragment : Fragment() {

    private val viewModel: CurrencyConverterViewModel by viewModels()

    private var _binding: FragmentCurrencyConverterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val windowInsets = ViewWindowInsetObserver(this).start()

        setContent {
            val uiState by viewModel.uiState.collectAsState(initial = UiState.EMPTY)
            CompositionLocalProvider(LocalWindowInsets provides windowInsets) {
                CurrencyConverterTheme {
                    if (uiState.isLoading || uiState.error != null) {
                        LoadingAndErrorComponent(
                            isLoading = uiState.isLoading,
                            error = uiState.error,
                            onRetry = { viewModel.retry() }
                        )
                    } else {
                        Text(text = "Hari")
                    }

                }
            }

        }

    }

    private fun buildUiModels(uiState: UiState) {
        binding.recyclerView.withModels {
            if (uiState.isLoading || uiState.error != null) {
                loadingAndErrorState {
                    id(R.id.loading_model)
                    isLoading(uiState.isLoading)
                    errorMessage(uiState.error?.let { getString(it.stringRes()) })
                    retryListener { _ -> viewModel.retry() }
                }
            } else {
                uiState.baseCurrency?.let { baseCurrency: Symbol ->
                    BaseCurrencyModel(
                        baseCurrency,
                        uiState.currencies?.firstOrNull()?.rate?.timeStamp,
                        convertCurrency = { viewModel.convertCurrency(it) })
                        .id(R.id.base_currency_model)
                        .addTo(this)
                }

                uiState.currencies?.forEach { currency ->
                    currencyCard {
                        id(currency.symbol.symbol)
                        symbol(currency.symbol)
                        amount(uiState.amount)
                        rate(currency.rate)
                        baseCurrency(uiState.baseCurrency)
                    }
                }

                btnAddCurrency {
                    id(R.id.add_currency_model)
                    clickListener { _ ->
                        addCurrency()
                    }

                }
            }
        }
    }

    private fun addCurrency() {
        setFragmentResultListener(SELECT_CURRENCY_REQUEST_KEY) { _, bundle ->
            if (bundle.getBoolean(IS_SELECTED)) {
                viewModel.convertCurrency()
            }
        }
        findNavController().navigate(R.id.symbolSelectionFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SELECT_CURRENCY_REQUEST_KEY = "select_currency_request_key"
        const val IS_SELECTED = "is_selected"
    }


}