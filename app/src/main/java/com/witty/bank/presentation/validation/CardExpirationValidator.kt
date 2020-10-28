package com.witty.bank.presentation.validation

import com.witty.bank.domain.validation.FieldValidator

class CardExpirationValidator(private val fieldValidator: FieldValidator) : Validator {

    override fun getValidationResult(text: String?): String? {
        return if (text.isNullOrEmpty()) {
            null
        } else {
            when (fieldValidator.validateCardExpiration(text)) {
                FieldValidator.CardExpirationValidationResult.OK -> ""
                FieldValidator.CardExpirationValidationResult.INVALID -> "Enter correct date"
            }
        }
    }
}