package com.witty.bank.android.system.di

import com.witty.bank.presentation.validation.FieldValidatorFactory
import com.witty.bank.presentation.validation.ValidatorFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ToolsModule.ToolsBindModule::class])
class ToolsModule {

    @Module
    interface ToolsBindModule {
        @Binds
        fun bindFieldValidatorFactory(factory: ValidatorFactory): FieldValidatorFactory
    }

}
