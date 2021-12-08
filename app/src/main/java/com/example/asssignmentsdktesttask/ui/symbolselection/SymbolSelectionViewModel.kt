package com.example.asssignmentsdktesttask.ui.symbolselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.domain.usecase.SearchSymbolUseCase
import com.example.asssignmentsdktesttask.domain.usecase.ToggleSymbolSelectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SymbolSelectionViewModel @Inject constructor(
    searchSymbolUseCase: SearchSymbolUseCase,
    private val toggleSymbolSelectionUseCase: ToggleSymbolSelectionUseCase
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val _symbols: Flow<List<Symbol>> = searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            searchSymbolUseCase(query)
        }
    val symbols: Flow<List<Symbol>> = _symbols


    fun toggleSelection(symbol: String) {
        viewModelScope.launch(Dispatchers.IO) {
            toggleSymbolSelectionUseCase(symbol)
        }
    }

}