package com.example.mycomposeteach.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeteach.model.dao.UserInfoDao
import com.example.mycomposeteach.model.entity.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val userInfoDao: UserInfoDao): ViewModel() {
    val allUser = userInfoDao.getAllUser()

    fun addUser(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userInfoDao.insert(UserInfo(0, name, password))
        }
    }

    fun deleteUser(userInfo: UserInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            userInfoDao.delete(userInfo)
        }
    }
}