package com.example.pwloginapp

import android.app.Application
import com.example.pwloginapp.di.networkModule
import com.example.pwloginapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule,repositoryModule))

        }
    }
}
