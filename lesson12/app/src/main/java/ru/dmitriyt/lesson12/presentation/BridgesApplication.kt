package ru.dmitriyt.lesson12.presentation

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.dmitriyt.lesson12.di.component.DaggerApplicationComponent

class BridgesApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }
}