package com.witty.bank.presentation.main.card.verification.result

import androidx.lifecycle.MutableLiveData
import com.witty.bank.domain.model.BankCard
import com.witty.bank.domain.usecase.BankCardObservableUseCase
import com.witty.bank.domain.usecase.defaultobserver.DefaultObserver
import com.witty.bank.navigation.NavigationCommand
import com.witty.bank.navigation.Navigator
import com.witty.bank.presentation.base.BaseViewModel
import com.witty.bank.presentation.main.MainActivityViewModel
import javax.inject.Inject
import javax.inject.Named

class PaymentResultViewModel @Inject constructor(@Named(MainActivityViewModel.APP_NAVIGATOR) private val navigator: Navigator,
                                                 private val bankCardUseCase: BankCardObservableUseCase) :
    BaseViewModel() {

    var bankCardLiveData = MutableLiveData<BankCard>()

    override fun onCleared() {
        bankCardUseCase.dispose()
        super.onCleared()
    }

    fun getBankCard() {
        bankCardUseCase.execute(object : DefaultObserver<BankCard>() {
            override fun onNext(t: BankCard) {
                bankCardLiveData.value = t
            }

        }, Unit)
    }

    fun onReturnClicked() {
        navigator.navigate(
            NavigationCommand.ToDirections(
                PaymentResultFragmentDirections.actionCardVerificationResultFragmentToHomeFragment()
            )
        )
    }
}