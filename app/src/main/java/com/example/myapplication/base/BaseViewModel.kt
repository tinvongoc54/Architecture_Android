package com.example.myapplication.base

import androidx.lifecycle.ViewModel
import com.example.myapplication.utils.liveData.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposible = CompositeDisposable()
    val isLoading = SingleLiveEvent<Boolean>()
    val onError = SingleLiveEvent<Throwable>()
}