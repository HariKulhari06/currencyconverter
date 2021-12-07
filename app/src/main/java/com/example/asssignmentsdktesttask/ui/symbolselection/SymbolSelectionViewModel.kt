package com.example.asssignmentsdktesttask.ui.symbolselection

import androidx.lifecycle.ViewModel
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.domain.usecase.SearchSymbolUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


@HiltViewModel
class SymbolSelectionViewModel @Inject constructor(
    searchSymbolUseCase: SearchSymbolUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("india")

    private val _symbols: Flow<List<Symbol>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            searchSymbolUseCase(query)
        }
    val symbols: Flow<List<Symbol>> = _symbols


    fun performSearch(query: String) {
        _searchQuery.value = query
    }


}