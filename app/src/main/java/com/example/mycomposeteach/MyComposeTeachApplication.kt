package com.example.mycomposeteach

import android.app.Application
import androidx.room.Room
import com.example.mycomposeteach.model.database.AccountDataBase

class MyComposeTeachApplication : Application() {
    lateinit var db: AccountDataBase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AccountDataBase::class.java, "accountDB").build()
    }
}