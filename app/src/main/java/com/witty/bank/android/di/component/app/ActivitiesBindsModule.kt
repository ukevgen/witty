package com.witty.bank.android.di.component.app

import com.witty.bank.presentation.main.MainActivity
import com.witty.bank.presentation.main.di.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        (MainActivityComponent::class)
    ]
)
abstract class ActivitiesBindsModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindMainActivityInjectorFactory(factory: MainActivityComponent.Factory): AndroidInjector.Factory<*>

}
