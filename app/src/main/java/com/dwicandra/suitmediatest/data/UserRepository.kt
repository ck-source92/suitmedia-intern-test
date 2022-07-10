package com.dwicandra.suitmediatest.data

class UserRepository private constructor(private val pref: UserPreference) {
    fun getUserPref(): UserPreference {
        return pref
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}