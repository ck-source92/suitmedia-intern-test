package com.dwicandra.suitmediatest.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dwicandra.suitmediatest.data.UserPreference
import com.dwicandra.suitmediatest.data.UserRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

object Injection {
    fun provideAuthRepository(context: Context): UserRepository {
        val preference = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(preference)
    }

}