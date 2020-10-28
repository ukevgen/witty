package com.witty.bank.presentation

import com.witty.bank.domain.model.CardType
import com.witty.bank.domain.model.ExpirationDate

object CardUtils {

    private val cardPrefixTypeMap = hashMapOf(
        "4" to CardType.VISA,
        "51" to CardType.MASTERCARD,
        "52" to CardType.MASTERCARD,
        "53" to CardType.MASTERCARD,
        "54" to CardType.MASTERCARD,
        "55" to CardType.MASTERCARD,
    )

    fun getCardType(cardNumber: String): CardType {
        if (cardNumber.isEmpty()) {
            return CardType.UNSUPPORTED
        } else {
            val visaCardPrefix = cardNumber.substring(0, 1)
            if (cardPrefixTypeMap.containsKey(visaCardPrefix)) {
                return cardPrefixTypeMap[visaCardPrefix]!!
            } else {
                if (cardNumber.length >= 2) {
                    val mastercardPrefix = cardNumber.substring(0, 2)
                    if (cardPrefixTypeMap.containsKey(mastercardPrefix)) {
                        return cardPrefixTypeMap[mastercardPrefix]!!
                    }
                }
            }
        }
        return CardType.UNSUPPORTED
    }

    fun getExpirationDate(date: String): ExpirationDate {
        val array = date.split("/")
        return ExpirationDate(array[0].toInt(), array[1].toInt())
    }
}