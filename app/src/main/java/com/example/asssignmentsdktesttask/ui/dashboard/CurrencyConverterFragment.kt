package com.example.asssignmentsdktesttask.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.btnAddCurrency
import com.example.asssignmentsdktesttask.currencyCard
import com.example.asssignmentsdktesttask.databinding.FragmentCurrencyConverterBinding
import com.example.asssignmentsdktesttask.lastSyncCell
import com.example.asssignmentsdktesttask.ui.symbolselection.SymbolSelectionFragment
import com.example.asssignmentsdktesttask.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterFragment : Fragment(R.layout.fragment_currency_converter) {

    private val viewModel: CurrencyConverterViewModel by viewModels()

    private var _binding: FragmentCurrencyConverterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCurrencyConverterBinding.bind(view)

        binding.recyclerView.addItemDecoration(
            SpacesItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.recycler_view_item_space
                )
            )
        )

        binding.recyclerView.withModels {

            lastSyncCell {
                id(11)
            }
            currencyCard {
                id(123)
            }

            currencyCard {
                id(124)
            }
            currencyCard {
                id(125)
            }

            btnAddCurrency {
                id(1)
                clickListener { _ ->
                    SymbolSelectionFragment().show(childFragmentManager, "")
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}