package com.example.asssignmentsdktesttask.ui.dashboard.views

import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.databinding.ItemBaseCurrencyCardBinding
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.ui.views.ViewBindingKotlinModel
import com.example.asssignmentsdktesttask.utils.formatUixTime


class BaseCurrencyModel(
    private val baseCurrency: Symbol,
    private val lastSyncTime: Long?,
    private val convertCurrency: (String) -> Unit
) : ViewBindingKotlinModel<ItemBaseCurrencyCardBinding>(R.layout.item_base_currency_card) {


    override fun ItemBaseCurrencyCardBinding.bind() {

        symbol = baseCurrency

        textViewLastSyncTime.isVisible = lastSyncTime != null

        lastSyncTime?.let {
            textViewLastSyncTime.text =
                textViewLastSyncTime.context.getString(R.string.last_update, formatUixTime(it))
        }

        textViewAmount.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                convertCurrency(textViewAmount.text.toString().trim())
            }
            false
        }

    }


    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return baseCurrency.hashCode()
    }
}