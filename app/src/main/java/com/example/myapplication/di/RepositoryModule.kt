package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.source.UserRepository
import com.example.myapplication.data.source.UserRepositoryImp
import com.example.myapplication.data.source.local.SharedPrefs
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val RepositoryModule = module {
    single { providerSharedPrefs(androidApplication()) }

    single<UserRepository> { UserRepositoryImp(get(), get()) }
}

fun providerSharedPrefs(context: Context): SharedPrefs = SharedPrefs(context)