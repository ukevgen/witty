package com.witty.bank.domain.usecase.defaultobserver

import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableCompletableObserver

open class DefaultCompletableObserver : DisposableCompletableObserver() {

    override fun onComplete() {
    }

    override fun onError(@NonNull e: Throwable) {
        e.printStackTrace()
    }
}