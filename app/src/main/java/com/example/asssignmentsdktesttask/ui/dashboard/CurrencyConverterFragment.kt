package com.example.asssignmentsdktesttask.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.btnAddCurrency
import com.example.asssignmentsdktesttask.currencyCard
import com.example.asssignmentsdktesttask.databinding.FragmentCurrencyConverterBinding
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.loadingAndErrorState
import com.example.asssignmentsdktesttask.ui.dashboard.views.BaseCurrencyModel
import com.example.asssignmentsdktesttask.utils.SpacesItemDecoration
import com.example.asssignmentsdktesttask.utils.ext.stringRes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyConverterFragment : Fragment(R.layout.fragment_currency_converter) {

    private val viewModel: CurrencyConverterViewModel by viewModels()

    private var _binding: FragmentCurrencyConverterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCurrencyConverterBinding.bind(view)

        initViews()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    buildUiModels(it)
                }
            }
        }
    }

    private fun initViews() {
        binding.recyclerView.addItemDecoration(
            SpacesItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.recycler_view_item_space
                )
            )
        )
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