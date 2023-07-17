package com.example.connectapplication

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConnectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ConnectApplication)
            modules(listOf(viewModelModule))
        }
    }
}