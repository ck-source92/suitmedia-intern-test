package com.dwicandra.suitmediatest.activity.First

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwicandra.suitmediatest.data.auth.User
import com.dwicandra.suitmediatest.data.auth.UserRepository
import kotlinx.coroutines.launch

class FirstViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.getUserPref().saveUser(
                user
            )
        }
    }
}