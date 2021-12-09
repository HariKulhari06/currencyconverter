package com.example.asssignmentsdktesttask.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.asssignmentsdktesttask.data.db.CacheDatabase
import com.example.asssignmentsdktesttask.data.db.entity.RateEntity
import com.google.common.truth.Truth
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
class RateDaoTest {
    @get:Rule
    val intendTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: CacheDatabase
    private lateinit var rateDao: RateDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CacheDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        rateDao = database.rateDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertRate() = runBlockingTest {
        val entity = RateEntity(
            symbol = "INR",
            rate = 12.1,
            date = "12/10/2021",
            timeStamp = 123456
        )
        rateDao.insert(entity)

        val rates = rateDao.getRates().first()

        Truth.assertThat(rates.any { entity.symbol == it.symbol }).isTrue()
    }
}