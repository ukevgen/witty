package com.witty.bank.data.cache

import com.witty.bank.data.datasource.local.UserLocalDataSource
import com.witty.bank.domain.model.BankCard
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor() : UserLocalDataSource {

    private val bankSubject = BehaviorSubject.createDefault(BankCard.getEmptyCard())

    override fun storeBankCard(bankCard: BankCard): Completable {
        return Completable.fromCallable {
            bankSubject.onNext(bankCard)
        }
    }

    override fun observeBankCard(): Observable<BankCard> {
        return bankSubject
    }
}