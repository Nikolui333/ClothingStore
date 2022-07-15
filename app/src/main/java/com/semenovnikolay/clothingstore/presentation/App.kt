package com.semenovnikolay.clothingstore.presentation

import android.app.Application
import com.semenovnikolay.clothingstore.presentation.di.add
import com.semenovnikolay.clothingstore.presentation.di.card
import com.semenovnikolay.clothingstore.presentation.di.favorite
import com.semenovnikolay.clothingstore.presentation.di.orderApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            // в androidLogger задаётся то, насколько подробно нужно выводить оишбки
            androidLogger(Level.ERROR)
            //inject Android context
            androidContext(this@App)

            modules(add, card, favorite, orderApi)

        }

    }
}