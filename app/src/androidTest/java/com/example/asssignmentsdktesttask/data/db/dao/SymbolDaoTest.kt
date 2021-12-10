package com.example.asssignmentsdktesttask.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import com.example.asssignmentsdktesttask.di.ForTesting
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class SymbolDaoTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val intendTaskExecutor = InstantTaskExecutorRule()


    @Inject
    @ForTesting
    lateinit var symbolDao: SymbolDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun insertSymbol() = runBlockingTest {
        val symbolEntity = SymbolEntity(
            symbol = "INR",
            fullName = "Indian Rupee",
            isSelected = false
        )
        symbolDao.insert(symbolEntity)

        val symbols = symbolDao.getSymbols().first()

        assertThat(symbols).contains(symbolEntity)
    }

}