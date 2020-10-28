package com.witty.bank.presentation.validation

interface Validator {
    fun getValidationResult(text: String?): String?
}