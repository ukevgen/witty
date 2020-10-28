package com.witty.bank.presentation.main.card.details.di

import androidx.lifecycle.ViewModel
import com.witty.bank.android.di.scopes.PerFragment
import com.witty.bank.android.di.viewmodelfactory.ViewModelKey
import com.witty.bank.presentation.main.card.details.CardDetailsFragment
import com.witty.bank.presentation.main.card.details.CardDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (CardDetailsFragmentComponent.FragmentBindingsModule::class),
        (CardDetailsFragmentComponent.FragmentModule::class),
        (CardDetailsFragmentComponent.FragmentBindsModule::class)
    ]
)
interface CardDetailsFragmentComponent : AndroidInjector<CardDetailsFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<CardDetailsFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(CardDetailsViewModel::class)
        abstract fun bindHomeViewModel(viewModel: CardDetailsViewModel): ViewModel
    }

    @Module
    class FragmentModule

    @Module
    interface FragmentBindsModule
}