package com.example.nikeshop.common

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class MySingleObserver<T> (private val compositeDisposable: CompositeDisposable):SingleObserver<T>{


    override fun onError(e: Throwable) {
        Timber.e(e)

    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)

    }
}