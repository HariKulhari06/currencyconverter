package com.example.asssignmentsdktesttask.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.btnAddCurrency
import com.example.asssignmentsdktesttask.currencyCard
import com.example.asssignmentsdktesttask.databinding.FragmentCurrencyConverterBinding
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.ui.dashboard.views.BaseCurrencyModel
import com.example.asssignmentsdktesttask.ui.symbolselection.SymbolSelectionFragment
import com.example.asssignmentsdktesttask.utils.SpacesItemDecoration
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
                return@withModels
            } else {
                uiState.baseCurrency?.let { baseCurrency: Symbol ->
                    BaseCurrencyModel(
                        lifecycleScope,
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
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getBoolean("bundleKey")
            if (result) {
                viewModel.convertCurrency("100")
            }
        }
        SymbolSelectionFragment().show(childFragmentManager, SymbolSelectionFragment.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}