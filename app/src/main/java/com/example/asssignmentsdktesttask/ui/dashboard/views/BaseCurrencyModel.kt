package com.example.asssignmentsdktesttask.ui.dashboard.views

import android.os.Build
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.databinding.ItemBaseCurrencyCardBinding
import com.example.asssignmentsdktesttask.domain.model.Symbol
import com.example.asssignmentsdktesttask.ui.views.ViewBindingKotlinModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.text.SimpleDateFormat
import java.util.*


class BaseCurrencyModel(
    private val lifecycleScope: LifecycleCoroutineScope,
    private val baseCurrency: Symbol,
    private val lastSyncTime: Long?,
    private val convertCurrency: (String) -> Unit
) : ViewBindingKotlinModel<ItemBaseCurrencyCardBinding>(R.layout.item_base_currency_card) {
    private var seconds = 1
    private var runTimer = true
    override fun ItemBaseCurrencyCardBinding.bind() {
        symbol = baseCurrency
        lastSyncTime?.let {
            textViewLastSyncTime.text = "Last updated: ${formatUixTime(it)}"
        }
        textViewAmount.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                convertCurrency(textViewAmount.text.toString().trim())
                seconds = 1
            }
            false
        }
        lifecycleScope.launch {
            launch {
                yield()
                while (runTimer) {
                    if (seconds == 60) {
                        runTimer = false
                        // progressBar.isIndeterminate = true
                        /// convertCurrency(textViewAmount.text.toString().trim())
                    } else {
                        delay(1000)
                        seconds += 1
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            progressBar.setProgress(seconds, true)
                        } else {
                            progressBar.progress = seconds
                        }
                        time.text = seconds.toString()
                    }
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return baseCurrency.hashCode()
    }


    private fun formatUixTime(timeStamp: Long): String {
        val date = Date(timeStamp.times(1000))
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT-4")
        return sdf.format(date)
    }
}