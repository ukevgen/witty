package com.witty.bank.presentation.main.card.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.witty.bank.domain.model.BankCard
import com.witty.bank.domain.model.CardType
import com.witty.bank.domain.usecase.StoreBankCardUseCase
import com.witty.bank.domain.usecase.defaultobserver.DefaultCompletableObserver
import com.witty.bank.domain.validation.FieldValidator
import com.witty.bank.extension.map
import com.witty.bank.navigation.NavigationCommand
import com.witty.bank.navigation.Navigator
import com.witty.bank.presentation.CardUtils
import com.witty.bank.presentation.base.BaseViewModel
import com.witty.bank.presentation.main.MainActivityViewModel
import com.witty.bank.presentation.validation.FieldValidatorFactory
import javax.inject.Inject
import javax.inject.Named

class CardDetailsViewModel @Inject constructor(@Named(MainActivityViewModel.APP_NAVIGATOR) private val navigator: Navigator,
                                               private val storeBankCardUseCase: StoreBankCardUseCase,
                                               private val validatorFactory: FieldValidatorFactory,
                                               private val fieldValidator: FieldValidator) :
    BaseViewModel() {

    var cardNumberLiveData = MutableLiveData<String>()

    private val cardValidator =
        validatorFactory.createValidator(FieldValidatorFactory.ValidatorType.CARD_NUMBER)

    private val cvvValidator =
        validatorFactory.createValidator(FieldValidatorFactory.ValidatorType.CVV)

    private val cardExpirationValidator =
        validatorFactory.createValidator(FieldValidatorFactory.ValidatorType.CARD_EXPIRATION)

    private val _cardNumberErrorLiveData: MutableLiveData<String> = MutableLiveData()
    private val _cardCVVErrorLiveData: MutableLiveData<String> = MutableLiveData()
    private val _cardExpirationErrorLiveData: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        storeBankCardUseCase.dispose()
        super.onCleared()
    }

    fun getCarNumberError(): LiveData<String> = _cardNumberErrorLiveData
    fun getCarCVVError(): LiveData<String> = _cardCVVErrorLiveData
    fun getCarExpirationError(): LiveData<String> = _cardExpirationErrorLiveData
    fun getCardType(): LiveData<CardType> = cardNumberLiveData.map {
        CardUtils.getCardType(it)
    }

    fun onContinueClicked(cardNumber: String?, cvv: String?, date: String?) {
        _cardNumberErrorLiveData.postValue(cardValidator.getValidationResult(cardNumber))
        _cardCVVErrorLiveData.postValue(cvvValidator.getValidationResult(cvv))
        _cardExpirationErrorLiveData.postValue(cardExpirationValidator.getValidationResult(date))

        if (isCardValid(cardNumber, cvv, date)) {

            getBankCard(cardNumber, cvv, date)?.let {
                storeBankCardUseCase.execute(object : DefaultCompletableObserver() {
                    override fun onComplete() {
                        navigator.navigate(
                            NavigationCommand.ToDirections(
                                CardDetailsFragmentDirections.actionCardDetailsFragmentToCardAmountFragment()
                            )
                        )
                    }
                }, StoreBankCardUseCase.Params(it))
            }
        }
    }

    // private

    private fun isCardValid(cardNumber: String?, cvv: String?, date: String?) =
        fieldValidator.validateCardNumber(cardNumber) == FieldValidator.CardNumberValidationResult.OK &&
                fieldValidator.validateCardExpiration(date) == FieldValidator.CardExpirationValidationResult.OK &&
                fieldValidator.validateCVV(cvv) == FieldValidator.CVVValidationResult.OK

    // derty, I know
    private fun getBankCard(cardNumber: String?, cvv: String?, date: String?): BankCard? {
        return if (cardNumber != null && cvv != null && date != null) {
            BankCard(
                cardNumber,
                cvv,
                CardUtils.getExpirationDate(date),
                CardUtils.getCardType(cardNumber)
            )
        } else {
            null
        }
    }
}