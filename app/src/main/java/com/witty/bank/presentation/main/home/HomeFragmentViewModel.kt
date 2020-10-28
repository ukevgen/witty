package com.witty.bank.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.witty.bank.navigation.NavigationCommand
import com.witty.bank.navigation.Navigator
import com.witty.bank.presentation.base.BaseViewModel
import com.witty.bank.presentation.main.MainActivityViewModel
import javax.inject.Inject
import javax.inject.Named

class HomeFragmentViewModel @Inject constructor(@Named(MainActivityViewModel.APP_NAVIGATOR) private val navigator: Navigator) :
    BaseViewModel() {

    private val _userBalanceLiveData = MutableLiveData(500.0)
    fun getUserBalance(): LiveData<Double> = _userBalanceLiveData
    fun onAddClicked() {
        navigator.navigate(
            NavigationCommand.ToDirections(
                HomeFragmentDirections.actionHomeFragmentToAccountFragment()
            )
        )
    }
}