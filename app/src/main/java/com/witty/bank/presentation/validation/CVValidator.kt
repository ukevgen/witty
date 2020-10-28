package com.witty.bank.presentation.validation

import com.witty.bank.domain.validation.FieldValidator

class CVValidator (private val fieldValidator: FieldValidator) : Validator {

    override fun getValidationResult(text: String?): String? {
        return if (text.isNullOrEmpty()) {
            null
        } else {
            when (fieldValidator.validateCVV(text)) {
                FieldValidator.CVVValidationResult.OK -> ""
                FieldValidator.CVVValidationResult.INVALID -> "Please enter full details"
            }
        }
    }
}