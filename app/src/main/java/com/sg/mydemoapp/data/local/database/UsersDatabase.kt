package com.sg.mydemoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sg.mydemoapp.data.local.dao.UserDao
import com.sg.mydemoapp.data.local.entity.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}