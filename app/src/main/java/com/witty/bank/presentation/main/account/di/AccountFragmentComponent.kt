package com.witty.bank.presentation.main.account.di

import androidx.lifecycle.ViewModel
import com.witty.bank.android.di.scopes.PerFragment
import com.witty.bank.android.di.viewmodelfactory.ViewModelKey
import com.witty.bank.presentation.main.account.AccountFragment
import com.witty.bank.presentation.main.account.AccountFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (AccountFragmentComponent.FragmentBindingsModule::class),
        (AccountFragmentComponent.FragmentModule::class),
        (AccountFragmentComponent.FragmentBindsModule::class)
    ]
)
interface AccountFragmentComponent : AndroidInjector<AccountFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<AccountFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(AccountFragmentViewModel::class)
        abstract fun bindViewModel(viewModel: AccountFragmentViewModel): ViewModel
    }

    @Module
    class FragmentModule

    @Module
    interface FragmentBindsModule
}