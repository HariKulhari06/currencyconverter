package com.example.asssignmentsdktesttask.ui.symbolselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asssignmentsdktesttask.databinding.FragmentSymbolSelectionBinding
import com.example.asssignmentsdktesttask.symbolSelectable
import com.example.asssignmentsdktesttask.ui.dashboard.CurrencyConverterFragment
import com.example.asssignmentsdktesttask.ui.dashboard.CurrencyConverterFragment.Companion.IS_SELECTED
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SymbolSelectionFragment : Fragment() {
    private val viewModel: SymbolSelectionViewModel by viewModels()

    private var _binding: FragmentSymbolSelectionBinding? = null
    private val binding get() = _binding!!

    private var isCurrencyAdded = false


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

        initViews()

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
                                            isCurrencyAdded = true
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

    private fun initViews() {
        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setFragmentResult(
                    CurrencyConverterFragment.SELECT_CURRENCY_REQUEST_KEY,
                    bundleOf(IS_SELECTED to isCurrencyAdded)
                )
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)

        binding.list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SymbolSelectionFragment"
    }

}