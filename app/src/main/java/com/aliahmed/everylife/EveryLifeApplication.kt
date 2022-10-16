package com.aliahmed.everylife

import android.app.Application
import com.aliahmed.everylife.di.appModule
import com.aliahmed.everylife.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EveryLifeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@EveryLifeApplication)
            androidLogger(Level.INFO)
            modules(listOf(networkModule, appModule))
        }
    }

}