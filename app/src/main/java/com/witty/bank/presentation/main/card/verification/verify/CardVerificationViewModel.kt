package com.witty.bank.presentation.main.card.verification.verify

import com.witty.bank.navigation.NavigationCommand
import com.witty.bank.navigation.Navigator
import com.witty.bank.presentation.base.BaseViewModel
import com.witty.bank.presentation.main.MainActivityViewModel
import javax.inject.Inject
import javax.inject.Named

class CardVerificationViewModel @Inject constructor(@Named(MainActivityViewModel.APP_NAVIGATOR) private val navigator: Navigator) :
    BaseViewModel() {

    fun onNextClicked() {
        navigator.navigate(
            NavigationCommand.ToDirections(
                CardVerificationFragmentDirections.actionCardVerificationFragmentToCardVerificationResultFragment()
            )
        )
    }
}