package com.witty.bank.android.di.component.app

import com.witty.bank.App
import com.witty.bank.android.di.module.ApplicationModule
import com.witty.bank.android.di.viewmodelfactory.ActivityViewModelModule
import com.witty.bank.android.system.di.AndroidModule
import com.witty.bank.android.system.di.ToolsModule
import com.witty.bank.data.cache.di.CacheModule
import com.witty.bank.data.di.DataModule
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        (ApplicationModule::class),
        (DataModule::class),
        (CacheModule::class),
        (ActivityViewModelModule::class),
        (AndroidModule::class),
        (ToolsModule::class),
        (AndroidSupportInjectionModule::class),
        (ActivitiesBindsModule::class),
        (ApplicationComponent.FragmentBindingsModule::class),
        (ApplicationComponent.ServiceBindingsModule::class)
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        abstract fun applicationModule(module: ApplicationModule): Builder
    }

    @Module(subcomponents = [])
    interface FragmentBindingsModule

    @Module(subcomponents = [])
    interface ServiceBindingsModule
}