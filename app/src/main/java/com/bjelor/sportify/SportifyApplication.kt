package com.bjelor.sportify

import android.app.Application
import com.bjelor.sportify.di.appModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SportifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            modules(appModule)
        }
    }
}
