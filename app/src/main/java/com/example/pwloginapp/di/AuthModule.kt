package com.example.pwloginapp.di

import AuthRepositoryImpl
import com.example.pwloginapp.data.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


val authModule = module {

    single { FirebaseAuth.getInstance() }

    single<AuthRepository> { AuthRepositoryImpl(get( )) }

}