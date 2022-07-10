package com.dwicandra.suitmediatest.activity.Second

import androidx.lifecycle.*
import com.dwicandra.suitmediatest.data.auth.User
import com.dwicandra.suitmediatest.data.auth.UserRepository
import com.dwicandra.suitmediatest.database.UserEntity

class SecondViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getUser(): LiveData<User> {
        return userRepository.getUserPref().getDataUser().asLiveData()
    }
}