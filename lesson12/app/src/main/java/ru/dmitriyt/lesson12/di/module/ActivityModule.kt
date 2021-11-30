package ru.dmitriyt.lesson12.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.dmitriyt.lesson12.presentation.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}