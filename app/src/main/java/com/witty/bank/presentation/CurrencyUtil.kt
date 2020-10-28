package com.witty.bank.presentation

import java.text.NumberFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object CurrencyUtil {

    private val currencyFormatters = ConcurrentHashMap<String, NumberFormat>()

    fun toCurrencyString(amount: Long, currencyCode: String): String {
        return getCurrencyFormatter(currencyCode).format(amount)
    }

    fun toCurrencyString(amount: Double, currencyCode: String): String {
        return getCurrencyFormatter(currencyCode).format(amount)
    }

    fun getCurrencyFormatter(currencyCode: String): NumberFormat {
        return currencyFormatters.getOrPut(currencyCode) {
            createCurrencyFormatter(currencyCode)
        }
    }

    private fun createCurrencyFormatter(currencyCode: String): NumberFormat {
        return NumberFormat.getCurrencyInstance()
            .apply { currency = Currency.getInstance(currencyCode) }
    }
}