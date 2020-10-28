package com.witty.bank.domain.model

class BankCard(val cardNumber: String,
               val cvv: String,
               val expirationDate: ExpirationDate,
               val type: CardType) {

    companion object {
        fun getEmptyCard() = BankCard("", "", ExpirationDate(0, 0), CardType.UNSUPPORTED)
    }

    fun getPaymentSystem() = when (type) {
        CardType.VISA -> "VISA"
        CardType.MASTERCARD -> "MasterCard"
        else -> "Unsupported"
    }

    fun getBankName() = "STARLING BANK"
    fun getFormattedExpirationPeriod() = expirationDate.toString()
    fun getFormattedCardNumber()  = cardNumber.onEachIndexed { index, c -> if (index%4==0) "$c " }
}

//1. Visa: the first digit is “4”
//2. MasterCard: first digits are “5” and the second digit is “1”, “2”, “3”, “4” or “5”.

enum class CardType {
    VISA,
    MASTERCARD,
    UNSUPPORTED
}