package com.example.asssignmentsdktesttask.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.asssignmentsdktesttask.data.db.CacheDatabase
import com.example.asssignmentsdktesttask.data.db.entity.SymbolEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SymbolDaoTest {

    @get:Rule
    val intendTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: CacheDatabase
    private lateinit var symbolDao: SymbolDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CacheDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        symbolDao = database.symbolDao()
    }


    @After
    fun tearDown() {
        database.close()
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