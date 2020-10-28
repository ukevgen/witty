package com.witty.bank.data.di

import com.witty.bank.data.repository.UserDataRepository
import com.witty.bank.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun provideUserRepository(repository: UserDataRepository): UserRepository
}