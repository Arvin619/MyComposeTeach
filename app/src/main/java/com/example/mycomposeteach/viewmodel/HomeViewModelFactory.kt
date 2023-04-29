package com.example.mycomposeteach.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycomposeteach.model.dao.UserInfoDao

class HomeViewModelFactory(private val userInfoDao: UserInfoDao): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(userInfoDao) as T
        }
        throw IllegalArgumentException("unknown viewModel class type")
    }
}