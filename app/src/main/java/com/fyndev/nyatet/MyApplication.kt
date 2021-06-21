package com.fyndev.nyatet

import android.app.Application
import com.fyndev.nyatet.di.databaseModule
import com.fyndev.nyatet.di.repositoryModule
import com.fyndev.nyatet.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}