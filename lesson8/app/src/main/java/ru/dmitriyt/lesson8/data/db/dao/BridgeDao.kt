package ru.dmitriyt.lesson8.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.dmitriyt.lesson8.data.db.entity.BridgeEntity

@Dao
interface BridgeDao {

    companion object {
        const val TABLE_NAME = "bridges"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBridges(vararg bridgeEntity: BridgeEntity)

    @Query("select * from $TABLE_NAME")
    suspend fun getBridges(): List<BridgeEntity>

    @Query("select * from $TABLE_NAME")
    fun getBridgesFlow(): Flow<List<BridgeEntity>>
}