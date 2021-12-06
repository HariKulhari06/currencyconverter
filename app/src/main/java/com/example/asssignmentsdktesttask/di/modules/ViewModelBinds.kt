package com.example.asssignmentsdktesttask.di.modules

import com.example.asssignmentsdktesttask.data.CurrencyRepositoryImpl
import com.example.asssignmentsdktesttask.data.db.CurrencyDatabaseImpl
import com.example.asssignmentsdktesttask.data.db.database.CurrencyDatabase
import com.example.asssignmentsdktesttask.model.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelBinds {

    @Binds
    abstract fun bindCurrencyDatabase(impl: CurrencyDatabaseImpl): CurrencyDatabase


    @Binds
    abstract fun bindCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository

}