package ru.dmitriyt.lesson12.data.remote

import retrofit2.http.GET
import ru.dmitriyt.lesson12.data.model.Bridge

interface BridgesApiService {
    @GET("bridges")
    suspend fun getBridges(): List<Bridge>
}