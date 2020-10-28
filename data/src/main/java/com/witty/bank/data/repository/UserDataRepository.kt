package com.witty.bank.data.repository

import com.witty.bank.data.datasource.local.UserLocalDataSource
import com.witty.bank.domain.model.BankCard
import com.witty.bank.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserDataRepository @Inject constructor(private val userLocalDataSource: UserLocalDataSource) :
    UserRepository {

    override fun storeBankCard(bankCard: BankCard): Completable {
        return userLocalDataSource.storeBankCard(bankCard)
    }

    override fun observeBankCard(): Observable<BankCard> {
        return userLocalDataSource.observeBankCard()
    }
}