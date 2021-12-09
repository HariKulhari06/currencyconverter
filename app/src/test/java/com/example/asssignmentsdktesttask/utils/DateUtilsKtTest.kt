package com.example.asssignmentsdktesttask.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DateUtilsKtTest {

    @Test
    fun `unix time stamp return human readable time format`() {
        val result = formatUixTime(1639031343)
        assertThat(result).isEqualTo("09 Dec 2021 02:29")
    }
}