package com.witty.bank.domain.model

data class ExpirationDate(val month: Int, val year: Int) {
    override fun toString() = "$month / $year"
}