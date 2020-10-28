package com.witty.bank.presentation.validation

import com.witty.bank.domain.validation.FieldValidator
import javax.inject.Inject

class ValidatorFactory @Inject constructor(private val fieldValidator: FieldValidator) :
    FieldValidatorFactory {

    override fun createValidator(validatorType: FieldValidatorFactory.ValidatorType): Validator =
        when (validatorType) {
            FieldValidatorFactory.ValidatorType.CARD_NUMBER -> CardNumberValidator(
                fieldValidator
            )
            FieldValidatorFactory.ValidatorType.CARD_EXPIRATION -> CardExpirationValidator(
                fieldValidator
            )
            FieldValidatorFactory.ValidatorType.CVV -> CVValidator(fieldValidator)
        }

}