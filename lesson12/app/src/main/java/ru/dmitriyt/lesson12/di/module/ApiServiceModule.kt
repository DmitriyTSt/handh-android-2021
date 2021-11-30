package ru.dmitriyt.lesson12.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dmitriyt.lesson12.data.remote.BridgesApiService
import javax.inject.Singleton

@Module
class ApiServiceModule {

    companion object {
        private const val BASE_URL = "http://gdemost.handh.ru:1235/"
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideBridgesApiService(
        retrofit: Retrofit
    ): BridgesApiService {
        return retrofit.create(BridgesApiService::class.java)
    }
}