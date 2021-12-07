package com.example.asssignmentsdktesttask.ui.symbolselection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.asssignmentsdktesttask.databinding.FragmentSymbolSelectionBinding
import com.example.asssignmentsdktesttask.symbolSelectable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SymbolSelectionFragment : BottomSheetDialogFragment() {
    private val viewModel: SymbolSelectionViewModel by viewModels()

    private var _binding: FragmentSymbolSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSymbolSelectionBinding.bind(view)


        lifecycleScope.launch {

            viewModel.symbols
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect { symbols ->
                    binding.list.withModels {
                        symbols.forEach { symbol ->
                            symbolSelectable {
                                id(symbol.symbol)
                                symbol(symbol)
                            }

                        }
                    }
                }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}