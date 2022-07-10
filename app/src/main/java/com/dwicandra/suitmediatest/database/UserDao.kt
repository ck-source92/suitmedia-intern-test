package com.dwicandra.suitmediatest.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun getAllUsers(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users WHERE id IN(:userId)")
    fun getHabitById(userId: Int): LiveData<UserEntity>
}