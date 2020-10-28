package com.witty.bank.presentation.main.card.amount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.witty.bank.domain.model.BankCard
import com.witty.bank.domain.usecase.BankCardObservableUseCase
import com.witty.bank.domain.usecase.defaultobserver.DefaultObserver
import com.witty.bank.navigation.NavigationCommand
import com.witty.bank.navigation.Navigator
import com.witty.bank.presentation.base.BaseViewModel
import com.witty.bank.presentation.main.MainActivityViewModel
import com.witty.bank.presentation.main.card.details.CardDetailsFragmentDirections
import javax.inject.Inject
import javax.inject.Named

class CardAmountViewModel @Inject constructor(private val bankCardObservableUseCase: BankCardObservableUseCase,
                                              @Named(MainActivityViewModel.APP_NAVIGATOR) private val navigator: Navigator) :
    BaseViewModel() {

    private val _bankCardLiveData = MutableLiveData<BankCard>()

    override fun onCleared() {
        bankCardObservableUseCase.clear()
        super.onCleared()
    }

    fun getBankCardLiveData(): LiveData<BankCard> = _bankCardLiveData

    fun observeBankCard() {
        bankCardObservableUseCase.execute(object : DefaultObserver<BankCard>() {
            override fun onNext(t: BankCard) {
                _bankCardLiveData.postValue(t)
            }

        }, Unit)
    }

    fun onAddMoneyClicked() {
        navigator.navigate(
            NavigationCommand.ToDirections(
                CardAmountFragmentDirections.actionCardAmountFragmentToCardVerificationFragment()
            )
        )
    }
}