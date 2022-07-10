package com.dwicandra.suitmediatest.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remotes_keys")
data class RemoteKeys(
    @PrimaryKey val id: Int?,
    val prevKey: Int?,
    val nextKey: Int?
)