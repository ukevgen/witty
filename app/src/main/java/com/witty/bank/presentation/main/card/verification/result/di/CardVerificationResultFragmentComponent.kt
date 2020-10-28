package com.witty.bank.presentation.main.card.verification.result.di

import androidx.lifecycle.ViewModel
import com.witty.bank.android.di.scopes.PerFragment
import com.witty.bank.android.di.viewmodelfactory.ViewModelKey
import com.witty.bank.presentation.main.card.verification.result.PaymentResultFragment
import com.witty.bank.presentation.main.card.verification.result.PaymentResultViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (CardVerificationResultFragmentComponent.FragmentBindingsModule::class),
        (CardVerificationResultFragmentComponent.FragmentModule::class),
        (CardVerificationResultFragmentComponent.FragmentBindsModule::class)
    ]
)
interface CardVerificationResultFragmentComponent :
    AndroidInjector<PaymentResultFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<PaymentResultFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(PaymentResultViewModel::class)
        abstract fun bindViewModel(viewModel: PaymentResultViewModel): ViewModel
    }

    @Module
    class FragmentModule

    @Module
    interface FragmentBindsModule
}