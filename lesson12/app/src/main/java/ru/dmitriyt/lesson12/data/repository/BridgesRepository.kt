package ru.dmitriyt.lesson12.data.repository

import kotlinx.coroutines.delay
import ru.dmitriyt.lesson12.data.model.Bridge
import ru.dmitriyt.lesson12.data.remote.BridgesApiService
import javax.inject.Inject

class BridgesRepository @Inject constructor(
    private val apiService: BridgesApiService,
) {

    suspend fun getBridges(): List<Bridge> {
        delay(1500L) // только чтобы было видно Loading состояние, не использовать
        return apiService.getBridges()
    }
}