package com.example.pwloginapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.pwloginapp.ui.theme.home.DashBoardViewModel
import com.example.pwloginapp.ui.theme.login.AuthViewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { DashBoardViewModel(get()) }

    viewModel { AuthViewModel(get()) }
}



