package com.witty.bank.data.datasource.local

import com.witty.bank.domain.model.BankCard
import io.reactivex.Completable
import io.reactivex.Observable

interface UserLocalDataSource {
    fun storeBankCard(bankCard: BankCard): Completable
    fun observeBankCard() : Observable<BankCard>

}