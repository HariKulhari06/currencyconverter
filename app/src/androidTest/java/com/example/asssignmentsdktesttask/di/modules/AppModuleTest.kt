package com.example.asssignmentsdktesttask.di.modules

import android.content.Context
import androidx.room.Room
import com.example.asssignmentsdktesttask.data.db.CacheDatabase
import com.example.asssignmentsdktesttask.di.ForTesting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModuleTest {

    @Provides
    @ForTesting
    fun provideInMemoryDatabase(@ApplicationContext context: Context): CacheDatabase = Room
        .inMemoryDatabaseBuilder(context, CacheDatabase::class.java)
        .allowMainThreadQueries()
        .build()


    @Provides
    @ForTesting
    fun provideSymbolDao(database: CacheDatabase) = database.symbolDao()

    @Provides
    @ForTesting
    fun provideRateDao(database: CacheDatabase) = database.rateDao()
}