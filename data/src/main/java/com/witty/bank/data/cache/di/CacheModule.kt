package com.witty.bank.data.cache.di

import com.witty.bank.data.cache.UserLocalDataSourceImpl
import com.witty.bank.data.datasource.local.UserLocalDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun provideUserLocalSource(userLocalDataSource: UserLocalDataSourceImpl): UserLocalDataSource
}