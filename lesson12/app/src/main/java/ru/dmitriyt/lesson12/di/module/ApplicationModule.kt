package ru.dmitriyt.lesson12.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dmitriyt.lesson12.presentation.BridgesApplication

@Module
open class ApplicationModule {

    @Provides
    fun provideContext(app: BridgesApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideApplication(app: BridgesApplication): Application {
        return app
    }
}