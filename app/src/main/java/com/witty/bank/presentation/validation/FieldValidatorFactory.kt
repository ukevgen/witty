package com.witty.bank.presentation.validation

interface FieldValidatorFactory {

    enum class ValidatorType {
        CARD_NUMBER,
        CARD_EXPIRATION,
        CVV
    }

    fun createValidator(validatorType: ValidatorType): Validator
}