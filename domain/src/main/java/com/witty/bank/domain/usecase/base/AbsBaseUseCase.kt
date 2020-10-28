package com.witty.bank.domain.usecase.base

import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class AbsBaseUseCase {
    private var disposables = CompositeDisposable()

    fun dispose() {
        disposables.dispose()
    }

    fun clear() {
        disposables.clear()
    }

    fun addDisposable(@NonNull disposable: Disposable) {
        disposables.add(disposable)
    }
}