package com.bjelor.sportify

import android.app.Application
import com.bjelor.sportify.database.di.databaseModule
import com.bjelor.sportify.di.appModule
import com.bjelor.sportify.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SportifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SportifyApplication)
            modules(
                appModule,
                databaseModule,
                remoteModule,
            )
        }
    }
}
