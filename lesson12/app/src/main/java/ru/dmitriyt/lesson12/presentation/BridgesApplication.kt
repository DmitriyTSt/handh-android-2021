package ru.dmitriyt.lesson12.presentation

import android.app.Application
import ru.dmitriyt.lesson12.di.ApplicationComponents

class BridgesApplication : Application() {
    lateinit var applicationComponents: ApplicationComponents

    override fun onCreate() {
        super.onCreate()
        applicationComponents = ApplicationComponents.getInstance(this)
    }
}