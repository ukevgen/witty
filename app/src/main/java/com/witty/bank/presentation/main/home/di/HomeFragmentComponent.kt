package com.witty.bank.presentation.main.home.di

import androidx.lifecycle.ViewModel
import com.witty.bank.android.di.scopes.PerFragment
import com.witty.bank.android.di.viewmodelfactory.ViewModelKey
import com.witty.bank.presentation.main.home.HomeFragment
import com.witty.bank.presentation.main.home.HomeFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (HomeFragmentComponent.FragmentBindingsModule::class),
        (HomeFragmentComponent.FragmentModule::class),
        (HomeFragmentComponent.FragmentBindsModule::class)
    ]
)
interface HomeFragmentComponent : AndroidInjector<HomeFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<HomeFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(HomeFragmentViewModel::class)
        abstract fun bindHomeViewModel(viewModel: HomeFragmentViewModel): ViewModel
    }

    @Module
    class FragmentModule

    @Module
    interface FragmentBindsModule
}