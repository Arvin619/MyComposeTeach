package com.example.mycomposeteach.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycomposeteach.model.dao.UserInfoDao
import com.example.mycomposeteach.model.entity.UserInfo

@Database(
    entities = [UserInfo::class],
    version = 1
)
abstract class AccountDataBase: RoomDatabase() {
    abstract val userInfoDao: UserInfoDao
}