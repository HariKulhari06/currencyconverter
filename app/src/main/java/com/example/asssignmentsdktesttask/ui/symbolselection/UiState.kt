package com.example.asssignmentsdktesttask.ui.symbolselection

import com.example.asssignmentsdktesttask.domain.model.Symbol

data class UiState(
    val isLoading: Boolean,
    val error: Throwable?,
    val searchQuery: String?,
    val symbols: List<Symbol>?
) {
    companion object {
        val EMPTY = UiState(
            isLoading = false,
            error = null,
            searchQuery = null,
            symbols = null
        )
    }
}
