package com.witty.bank.presentation.main.card.amount.di

import androidx.lifecycle.ViewModel
import com.witty.bank.android.di.scopes.PerFragment
import com.witty.bank.android.di.viewmodelfactory.ViewModelKey
import com.witty.bank.presentation.main.card.amount.CardAmountFragment
import com.witty.bank.presentation.main.card.amount.CardAmountViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (CardAmountFragmentComponent.FragmentBindingsModule::class),
        (CardAmountFragmentComponent.FragmentModule::class),
        (CardAmountFragmentComponent.FragmentBindsModule::class)
    ]
)
interface CardAmountFragmentComponent : AndroidInjector<CardAmountFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<CardAmountFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(CardAmountViewModel::class)
        abstract fun bindHomeViewModel(viewModel: CardAmountViewModel): ViewModel
    }

    @Module
    class FragmentModule

    @Module
    interface FragmentBindsModule
}