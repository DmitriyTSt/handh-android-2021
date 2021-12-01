package ru.dmitriyt.lesson12.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.dmitriyt.lesson12.data.model.Bridge

interface BridgesApiService {
    companion object {
        private const val BASE_URL = "http://gdemost.handh.ru:1235/"

        fun create(): BridgesApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BridgesApiService::class.java)
        }
    }

    @GET("bridges")
    suspend fun getBridges(): List<Bridge>
}