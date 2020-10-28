package com.witty.bank.presentation.main.card.verification.verify.di

import androidx.lifecycle.ViewModel
import com.witty.bank.android.di.scopes.PerFragment
import com.witty.bank.android.di.viewmodelfactory.ViewModelKey
import com.witty.bank.presentation.main.card.verification.verify.CardVerificationFragment
import com.witty.bank.presentation.main.card.verification.verify.CardVerificationViewModel
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
        (CardVerificationFragmentComponent.FragmentBindingsModule::class),
        (CardVerificationFragmentComponent.FragmentModule::class),
        (CardVerificationFragmentComponent.FragmentBindsModule::class)
    ]
)
interface CardVerificationFragmentComponent : AndroidInjector<CardVerificationFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<CardVerificationFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(CardVerificationViewModel::class)
        abstract fun bindViewModel(viewModel: CardVerificationViewModel): ViewModel
    }

    @Module
    class FragmentModule

    @Module
    interface FragmentBindsModule
}