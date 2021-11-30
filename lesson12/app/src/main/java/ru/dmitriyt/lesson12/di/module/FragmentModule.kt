package ru.dmitriyt.lesson12.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.dmitriyt.lesson12.presentation.list.BridgeListFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bridgeListFragment(): BridgeListFragment
}