package com.example.asssignmentsdktesttask.di.modules

import android.content.Context
import androidx.room.Room
import com.example.asssignmentsdktesttask.data.db.CacheDatabase
import com.example.asssignmentsdktesttask.di.annotation.DatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String {
        return "fixer.db"
    }

    @Provides
    @Singleton
    fun provideCacheDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String,
    ): CacheDatabase {
        return Room
            .databaseBuilder(context, CacheDatabase::class.java, databaseName)
            .build()
    }

    @Provides
    fun provideSymbolDao(database: CacheDatabase) = database.symbolDao()

}
