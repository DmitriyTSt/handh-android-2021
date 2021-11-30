package ru.dmitriyt.lesson12.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.dmitriyt.lesson12.di.module.ActivityModule
import ru.dmitriyt.lesson12.di.module.ApiServiceModule
import ru.dmitriyt.lesson12.di.module.FragmentModule
import ru.dmitriyt.lesson12.di.module.ViewModelFactoryModule
import ru.dmitriyt.lesson12.di.module.ViewModelModule
import ru.dmitriyt.lesson12.presentation.BridgesApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,

        ApiServiceModule::class,
        ViewModelFactoryModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<BridgesApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BridgesApplication): ApplicationComponent
    }
}