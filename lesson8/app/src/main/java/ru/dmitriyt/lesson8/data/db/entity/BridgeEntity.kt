package ru.dmitriyt.lesson8.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.dmitriyt.lesson8.data.db.dao.BridgeDao

@Entity(tableName = BridgeDao.TABLE_NAME)
data class BridgeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "name_eng") val nameEng: String,
) {
    override fun toString(): String {
        return "$id $name ($nameEng)"
    }
}