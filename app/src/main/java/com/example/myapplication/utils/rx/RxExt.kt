package com.example.myapplication.utils.rx

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.utils.schedulerProvider.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single

fun Completable.async(schedulerProvider: SchedulerProvider) =
    subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())

fun Completable.loading(liveData: MutableLiveData<Boolean>) =
    doOnSubscribe { liveData.value = true }.doFinally { liveData.value = false }

fun Completable.loading(block: (Boolean) -> Unit) =
    doOnSubscribe { block(true) }.doFinally { block(false) }

fun <T> Single<T>.async(schedulerProvider: SchedulerProvider) =
    subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())

fun <T> Single<T>.loading(liveData: MutableLiveData<Boolean>) =
    doOnSubscribe { liveData.value = true }.doFinally { liveData.value = false }

fun <T> Single<T>.loading(block: (Boolean) -> Unit) =
    doOnSubscribe { block(true) }.doFinally { block(false) }

