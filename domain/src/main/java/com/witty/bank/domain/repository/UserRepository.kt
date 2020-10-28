package com.witty.bank.domain.repository

import com.witty.bank.domain.model.BankCard
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {
    fun storeBankCard(bankCard: BankCard): Completable
    fun observeBankCard() : Observable<BankCard>
}