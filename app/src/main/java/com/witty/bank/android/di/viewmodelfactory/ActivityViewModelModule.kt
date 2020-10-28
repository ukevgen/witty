package com.witty.bank.android.di.viewmodelfactory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: WittyViewModelFactory): ViewModelProvider.Factory

}