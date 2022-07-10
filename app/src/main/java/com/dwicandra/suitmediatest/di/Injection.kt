package com.dwicandra.suitmediatest.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dwicandra.suitmediatest.data.auth.UserPreference
import com.dwicandra.suitmediatest.data.auth.UserRepository
import com.dwicandra.suitmediatest.data.core.MainRepository
import com.dwicandra.suitmediatest.database.UserDatabase
import com.dwicandra.suitmediatest.network.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

object Injection {
    fun provideAuthRepository(context: Context): UserRepository {
        val preference = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(preference)
    }

    fun provideMainRepository(context: Context): MainRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return MainRepository(database, apiService)
    }

}