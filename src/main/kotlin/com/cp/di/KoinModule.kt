package com.cp.di

import com.cp.repository.HeroRepository
import com.cp.repository.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}
