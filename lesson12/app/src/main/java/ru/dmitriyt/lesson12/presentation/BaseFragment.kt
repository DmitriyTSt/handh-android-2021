package ru.dmitriyt.lesson12.presentation

import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import ru.dmitriyt.lesson12.di.ApplicationComponents

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    val viewModelFactory by lazy { applicationComponents.provideViewModelFactory() }

    protected val applicationComponents: ApplicationComponents
        get() = (requireActivity().application as BridgesApplication).applicationComponents

    @MainThread
    inline fun <reified VM : ViewModel> Fragment.appViewModels() =
        createViewModelLazy(VM::class, { this.viewModelStore }, { viewModelFactory })
}