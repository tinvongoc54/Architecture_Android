package com.example.myapplication.di

import com.example.myapplication.features.home.HomeViewModel
import com.example.myapplication.features.userDetail.UserDetailViewModel
import com.example.myapplication.utils.schedulerProvider.SchedulerProvider
import com.example.myapplication.utils.schedulerProvider.SchedulerProviderImp
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * Created by TinVn on 10/14/19.
 *
 */

val AppModule = module {
    viewModel { HomeViewModel(get()) }

    viewModel { UserDetailViewModel() }

    single<SchedulerProvider>(createdAtStart = true) { SchedulerProviderImp() }
}

val rootModule = listOf(AppModule, NetworkModule, RepositoryModule)