package com.witty.bank.domain.validation

import com.witty.bank.domain.model.CardType
import org.joda.time.DateTime
import javax.inject.Inject

class FieldValidator @Inject constructor() {

    private val yearOfCentury = DateTime().yearOfCentury().get()

    private companion object {

        const val CARD_LENGTH_WITH_SPACES = 19
        const val CARD_EXPIRATION_LENGTH_WITH_SLASH = 5
        const val CVV_LENGTH = 3
    }

    private val cardPrefixTypeMap = hashMapOf(
        "4" to CardType.VISA,
        "51" to CardType.MASTERCARD,
        "52" to CardType.MASTERCARD,
        "53" to CardType.MASTERCARD,
        "54" to CardType.MASTERCARD,
        "55" to CardType.MASTERCARD,
    )

    fun validateCardNumber(cardNumber: String?): CardNumberValidationResult {
        if (cardNumber.isNullOrEmpty() || cardNumber.length != CARD_LENGTH_WITH_SPACES) return CardNumberValidationResult.INVALID
        if (cardPrefixTypeMap.containsKey(
                cardNumber.substring(0, 1)
            ) || cardPrefixTypeMap.containsKey(cardNumber.substring(0, 2))
        ) {
            return CardNumberValidationResult.OK
        }
        return CardNumberValidationResult.INVALID
    }

    fun validateCardExpiration(cardExpiration: String?): CardExpirationValidationResult {
        if (cardExpiration.isNullOrEmpty() || cardExpiration.length != CARD_EXPIRATION_LENGTH_WITH_SLASH) return CardExpirationValidationResult.INVALID
        val month = cardExpiration.substring(0, 2).toInt()
        val year = cardExpiration.substring(3, 5).toInt()

        if (month !in (1..12) || year < yearOfCentury) return CardExpirationValidationResult.INVALID

        return CardExpirationValidationResult.OK
    }

    fun validateCVV(cvv: String?): CVVValidationResult {
        return when (cvv?.length) {
            CVV_LENGTH -> CVVValidationResult.OK
            else -> CVVValidationResult.INVALID
        }
    }

    enum class CardNumberValidationResult {
        OK,
        INVALID
    }

    enum class CardExpirationValidationResult {
        OK,
        INVALID
    }

    enum class CVVValidationResult {
        OK,
        INVALID
    }

}