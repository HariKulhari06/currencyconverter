package com.example.asssignmentsdktesttask.ui.symbolselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSymbolSelectionBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { dismiss() }

        binding.list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.symbols
                        .collect { symbols ->
                            binding.list.withModels {
                                symbols.forEach { symbol ->
                                    symbolSelectable {
                                        id(symbol.symbol)
                                        symbol(symbol)
                                        clickListener { _ ->
                                            viewModel.toggleSelection(symbol.symbol)
                                        }
                                    }
                                }
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

    companion object {
        const val TAG = "SymbolSelectionFragment"
    }

}