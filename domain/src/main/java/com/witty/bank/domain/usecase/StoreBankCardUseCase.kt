package com.witty.bank.domain.usecase

import com.witty.bank.domain.model.BankCard
import com.witty.bank.domain.repository.UserRepository
import com.witty.bank.domain.usecase.base.AbsUseCaseCompl
import io.reactivex.Completable
import javax.inject.Inject

class StoreBankCardUseCase @Inject constructor(private val userRepository: UserRepository) :
    AbsUseCaseCompl<StoreBankCardUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return userRepository.storeBankCard(params.bankCard)
    }

    class Params(val bankCard: BankCard)
}