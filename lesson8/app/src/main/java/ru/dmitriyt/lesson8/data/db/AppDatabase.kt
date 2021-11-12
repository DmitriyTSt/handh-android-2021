package ru.dmitriyt.lesson8.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dmitriyt.lesson8.data.db.dao.BridgeDao
import ru.dmitriyt.lesson8.data.db.entity.BridgeEntity

@Database(
    entities = [
        BridgeEntity::class,
    ],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "app_database"
    }

    abstract fun bridgeDao(): BridgeDao
}