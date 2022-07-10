package com.dwicandra.suitmediatest.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwicandra.suitmediatest.activity.First.FirstViewModel
import com.dwicandra.suitmediatest.activity.Second.SecondViewModel
import com.dwicandra.suitmediatest.di.Injection
import com.dwicandra.suitmediatest.data.auth.UserRepository

class AuthViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FirstViewModel::class.java) -> {
                FirstViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(SecondViewModel::class.java) -> {
                SecondViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: AuthViewModelFactory? = null
        fun getInstance(context: Context): AuthViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: AuthViewModelFactory(Injection.provideAuthRepository(context))
            }.also { instance = it }
    }
}