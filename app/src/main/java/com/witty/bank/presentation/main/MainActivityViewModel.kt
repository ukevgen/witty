package com.witty.bank.presentation.main

import com.witty.bank.navigation.Navigator
import com.witty.bank.presentation.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Named

class MainActivityViewModel @Inject constructor(@Named(APP_NAVIGATOR) private val navigator: Navigator) :
    BaseViewModel() {

    // Just to show different variants
    var amountToCharge = 0.0

    companion object {
        const val APP_NAVIGATOR = "com.witty.bank.presentation.main.MainActivityViewModel"
    }
}