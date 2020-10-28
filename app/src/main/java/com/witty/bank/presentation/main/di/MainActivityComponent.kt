package com.witty.bank.presentation.main.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.witty.bank.android.di.scopes.PerActivity
import com.witty.bank.android.di.viewmodelfactory.ViewModelKey
import com.witty.bank.android.system.ContextHolder
import com.witty.bank.navigation.AppNavComponentsNavigator
import com.witty.bank.navigation.Navigator
import com.witty.bank.presentation.main.MainActivity
import com.witty.bank.presentation.main.MainActivityViewModel
import com.witty.bank.presentation.main.account.AccountFragment
import com.witty.bank.presentation.main.account.di.AccountFragmentComponent
import com.witty.bank.presentation.main.card.amount.CardAmountFragment
import com.witty.bank.presentation.main.card.amount.di.CardAmountFragmentComponent
import com.witty.bank.presentation.main.card.details.CardDetailsFragment
import com.witty.bank.presentation.main.card.details.di.CardDetailsFragmentComponent
import com.witty.bank.presentation.main.card.verification.result.PaymentResultFragment
import com.witty.bank.presentation.main.card.verification.result.di.CardVerificationResultFragmentComponent
import com.witty.bank.presentation.main.card.verification.verify.CardVerificationFragment
import com.witty.bank.presentation.main.card.verification.verify.di.CardVerificationFragmentComponent
import com.witty.bank.presentation.main.home.HomeFragment
import com.witty.bank.presentation.main.home.di.HomeFragmentComponent
import com.witty.bank.navigation.AppNavProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Named

@PerActivity
@Subcomponent(
    modules = [
        (MainActivityComponent.ActivityBindsModule::class),
        (MainActivityComponent.FragmentBindingsModule::class),
        (MainActivityComponent.ActivityModule::class)
    ]
)
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<MainActivity>

    @Module
    interface ActivityBindsModule {

        @Binds
        fun provideActivityModule(activity: MainActivity): AppCompatActivity

        @Binds
        fun bindNavProvider(activity: MainActivity): AppNavProvider

        @Binds
        @IntoMap
        @ViewModelKey(MainActivityViewModel::class)
        abstract fun bindMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
    }

    @Module
    class ActivityModule {

        @Provides
        @Named(MainActivityViewModel.APP_NAVIGATOR)
        fun provideCompNavigator(
            provider: AppNavProvider,
            contextHolder: ContextHolder,
        ): Navigator = AppNavComponentsNavigator(
            provider.getNavController(),
            { provider.finish() },
            contextHolder
        )
    }

    @Module(
        subcomponents = [
            HomeFragmentComponent::class,
            CardAmountFragmentComponent::class,
            CardDetailsFragmentComponent::class,
            CardVerificationFragmentComponent::class,
            CardVerificationResultFragmentComponent::class,
            AccountFragmentComponent::class
        ]
    )
    interface FragmentBindingsModule {
        @Binds
        @IntoMap
        @ClassKey(HomeFragment::class)
        fun bindHomeFragment(factory: HomeFragmentComponent.Factory): AndroidInjector.Factory<*>

        @Binds
        @IntoMap
        @ClassKey(CardAmountFragment::class)
        fun bindCardAmountFragment(factory: CardAmountFragmentComponent.Factory): AndroidInjector.Factory<*>

        @Binds
        @IntoMap
        @ClassKey(CardDetailsFragment::class)
        fun bindCardDetailsFragment(factory: CardDetailsFragmentComponent.Factory): AndroidInjector.Factory<*>

        @Binds
        @IntoMap
        @ClassKey(CardVerificationFragment::class)
        fun bindCardVerificationFragment(factory: CardVerificationFragmentComponent.Factory): AndroidInjector.Factory<*>

        @Binds
        @IntoMap
        @ClassKey(PaymentResultFragment::class)
        fun bindCardVerificationResultFragment(factory: CardVerificationResultFragmentComponent.Factory): AndroidInjector.Factory<*>

        @Binds
        @IntoMap
        @ClassKey(AccountFragment::class)
        fun bindAccountFragment(factory: AccountFragmentComponent.Factory): AndroidInjector.Factory<*>

    }
}