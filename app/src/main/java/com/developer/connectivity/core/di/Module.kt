package com.developer.connectivity.core.di

import com.developer.connectivity.core.connectivity.AndroidConnectivityManager
import com.developer.connectivity.core.connectivity.ConnectivityObserver
import com.developer.connectivity.presentation.viewmodel.ConnectivityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AndroidConnectivityManager.getInstance(get()) }
    factory { ConnectivityObserver(get()) }
    viewModel { ConnectivityViewModel(get()) }
}