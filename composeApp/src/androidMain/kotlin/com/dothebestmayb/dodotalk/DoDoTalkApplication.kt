package com.dothebestmayb.dodotalk

import android.app.Application
import com.dothebestmayb.dodotalk.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.KoinConfiguration

class DoDoTalkApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@DoDoTalkApplication)
            androidLogger()
        }
    }
}
