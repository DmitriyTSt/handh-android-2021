package ru.dmitriyt.lesson8.data.db

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import ru.dmitriyt.lesson8.data.db.entity.BridgeEntity

class DatabaseClient private constructor(
    context: Context,
) {
    companion object {
        private var instance: DatabaseClient? = null

        fun getInstance(context: Context): DatabaseClient {
            return instance ?: run {
                val newInstance = DatabaseClient(context)
                instance = newInstance
                newInstance
            }
        }
    }

    private val db = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()

    suspend fun saveBridges(bridges: List<BridgeEntity>) {
        db.bridgeDao().saveBridges(*bridges.toTypedArray())
    }

    suspend fun getBridges(): List<BridgeEntity> {
        return db.bridgeDao().getBridges()
    }

    fun getBridgesFlow(): Flow<List<BridgeEntity>> {
        return db.bridgeDao().getBridgesFlow()
    }
}