package com.example.awoman.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val showProgressBar = MutableLiveData<Boolean>()
    override fun onCleared() {
        compositeDisposable.clear()

        super.onCleared()

    }
}