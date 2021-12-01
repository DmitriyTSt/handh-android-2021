package ru.dmitriyt.lesson12.di

import android.annotation.SuppressLint
import android.content.Context
import ru.dmitriyt.lesson12.data.remote.BridgesApiService
import ru.dmitriyt.lesson12.data.repository.BridgesRepository

class ApplicationComponents private constructor(private val context: Context) {

    private val bridgesApiService by lazy { BridgesApiService.create() }
    private val bridgesRepository by lazy { BridgesRepository(provideBridgesApiService()) }
    private val customViewModelProviderFactory by lazy { CustomViewModelProviderFactory(this) }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ApplicationComponents? = null

        fun getInstance(context: Context): ApplicationComponents {
            return instance ?: run {
                val instance = ApplicationComponents(context)
                this.instance = instance
                return instance
            }
        }
    }

    fun provideBridgesApiService(): BridgesApiService {
        return bridgesApiService
    }

    fun provideBridgesRepository(): BridgesRepository {
        return bridgesRepository
    }

    fun provideViewModelFactory(): CustomViewModelProviderFactory {
        return customViewModelProviderFactory
    }
}