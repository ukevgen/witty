package com.witty.bank.presentation.validation

import com.witty.bank.domain.validation.FieldValidator

class CardNumberValidator(private val fieldValidator: FieldValidator) : Validator {

    override fun getValidationResult(text: String?): String? {
        return if (text.isNullOrEmpty()) {
            null
        } else {
            when (fieldValidator.validateCardNumber(text)) {
                FieldValidator.CardNumberValidationResult.OK -> ""
                FieldValidator.CardNumberValidationResult.INVALID -> "Please enter a valid card number"
            }
        }
    }
}