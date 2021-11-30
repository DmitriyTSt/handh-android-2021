package ru.dmitriyt.lesson12.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.dmitriyt.lesson12.di.util.ViewModelKey
import ru.dmitriyt.lesson12.presentation.list.BridgeListViewModel

@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BridgeListViewModel::class)
    abstract fun bridgeListViewModel(viewModel: BridgeListViewModel): ViewModel
}