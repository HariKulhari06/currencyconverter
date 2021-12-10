package com.example.asssignmentsdktesttask.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.asssignmentsdktesttask.data.db.entity.RateEntity
import com.example.asssignmentsdktesttask.di.ForTesting
import com.google.common.truth.Truth
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
class RateDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val intendTaskExecutor = InstantTaskExecutorRule()

    @Inject
    @ForTesting
    lateinit var rateDao: RateDao

    @Before
    fun setUp() {
        hiltRule.inject()
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