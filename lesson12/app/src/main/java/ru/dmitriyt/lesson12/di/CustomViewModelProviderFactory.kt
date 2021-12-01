package ru.dmitriyt.lesson12.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.dmitriyt.lesson12.presentation.list.BridgeListViewModel

class CustomViewModelProviderFactory(
    private val applicationComponents: ApplicationComponents,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass) {
            BridgeListViewModel::class.java -> BridgeListViewModel(
                bridgesRepository = applicationComponents.provideBridgesRepository()
            ) as T
            else -> throw IllegalStateException("unsupported viewModel class")
        }
    }
}