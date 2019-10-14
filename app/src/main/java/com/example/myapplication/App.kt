package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.rootModule



class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, rootModule)
    }
}