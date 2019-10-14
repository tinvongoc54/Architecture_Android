package com.example.myapplication.utils

import androidx.lifecycle.Observer

class SafeObserver<T>(private val notifier: (T) -> Unit) : Observer<T> {
    override fun onChanged(t: T) {
        t?.let { notifier(t) }
    }
}