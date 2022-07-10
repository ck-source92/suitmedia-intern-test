package com.dwicandra.suitmediatest.data.core

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.dwicandra.suitmediatest.database.UserDatabase
import com.dwicandra.suitmediatest.database.UserEntity
import com.dwicandra.suitmediatest.network.ApiService

class MainRepository(private val userDatabase: UserDatabase, private val apiService: ApiService) {

    fun getAllUser(): LiveData<PagingData<UserEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 12
            ),
            remoteMediator = UserRemoteMediator(userDatabase, apiService),
            pagingSourceFactory = {
                userDatabase.usersDao().getAllUsers()
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(
            apiService: ApiService,
            database: UserDatabase
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(database, apiService)
            }.also { instance = it }
    }
}