package com.example.asssignmentsdktesttask.domain.model

data class Symbol(
    val symbol: String,
    val fullName: String,
    val isSelected: Boolean = false
) {
    fun getSymbolWithFillName() = "$symbol - $fullName"
}
