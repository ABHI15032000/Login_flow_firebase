package com.example.pwloginapp.di

import com.example.pwloginapp.data.auth.AuthRepository
import com.example.pwloginapp.data.auth.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


val authModule = module {

    single { FirebaseAuth.getInstance() }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}