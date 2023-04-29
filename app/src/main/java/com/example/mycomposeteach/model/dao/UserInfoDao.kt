package com.example.mycomposeteach.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycomposeteach.model.entity.UserInfo

@Dao
interface UserInfoDao {
    @Delete
    suspend fun delete(userInfo: UserInfo): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userInfo: UserInfo): Long

    @Query("SELECT * FROM ${UserInfo.TABLE_NAME}")
    fun getAllUser(): LiveData<List<UserInfo>>
}