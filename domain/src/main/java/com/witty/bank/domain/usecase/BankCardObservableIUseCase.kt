package com.witty.bank.domain.usecase

import com.witty.bank.domain.model.BankCard
import com.witty.bank.domain.repository.UserRepository
import com.witty.bank.domain.usecase.base.AbsUseCaseObs
import io.reactivex.Observable
import javax.inject.Inject

class BankCardObservableUseCase @Inject constructor(private val userRepository: UserRepository) :
    AbsUseCaseObs<BankCard, Unit>() {

    override fun buildUseCaseObservable(params: Unit): Observable<BankCard> {
        return userRepository.observeBankCard()
    }
}