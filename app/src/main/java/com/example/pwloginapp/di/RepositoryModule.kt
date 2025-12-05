package com.example.pwloginapp.di

import com.example.pwloginapp.data.repository.DashboardRepository
import com.example.pwloginapp.data.repository.DashboardRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<DashboardRepository> { DashboardRepositoryImpl(get()) as DashboardRepository }
}
