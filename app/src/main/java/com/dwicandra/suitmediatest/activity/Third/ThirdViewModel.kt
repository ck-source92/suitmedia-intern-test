package com.dwicandra.suitmediatest.activity.Third

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dwicandra.suitmediatest.data.core.MainRepository
import com.dwicandra.suitmediatest.database.UserEntity

class ThirdViewModel(mainRepository: MainRepository) : ViewModel() {
    val users: LiveData<PagingData<UserEntity>> =
        mainRepository.getAllUser().cachedIn(viewModelScope)
}