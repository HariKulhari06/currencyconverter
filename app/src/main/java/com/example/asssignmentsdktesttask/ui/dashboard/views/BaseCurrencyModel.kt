package com.example.asssignmentsdktesttask.ui.dashboard.views

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.databinding.ItemBaseCurrencyCardBinding
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.ui.views.ViewBindingKotlinModel


class BaseCurrencyModel(
    private val baseCurrency: Symbol,
    private val convertCurrency: (String) -> Unit
) : ViewBindingKotlinModel<ItemBaseCurrencyCardBinding>(R.layout.item_base_currency_card) {
    override fun ItemBaseCurrencyCardBinding.bind() {
        symbol = baseCurrency


        textViewAmount.setOnEditorActionListener(OnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                convertCurrency(textViewAmount.text.toString().trim())
            }
            false
        })
    }
}