package com.example.wongnaiassignment

import androidx.multidex.MultiDexApplication
import com.example.data.dataModule
import com.example.domain.domainModule
import com.example.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            logger(PrintLogger(Level.DEBUG))
            androidContext(this@MainApplication)
            modules(dataModule)
            modules(domainModule)
            modules(presentationModule)
        }

    }

}