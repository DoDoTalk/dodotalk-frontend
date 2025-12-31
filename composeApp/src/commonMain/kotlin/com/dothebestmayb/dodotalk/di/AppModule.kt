package com.dothebestmayb.dodotalk.di

import com.dothebestmayb.dodotalk.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}
