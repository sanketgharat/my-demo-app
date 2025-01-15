package com.sg.mydemoapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.sg.mydemoapp.data.local.entity.User

@Dao
interface UserDao {

    @Upsert()
    suspend fun insertData(list: List<User>)

    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id = :userId")
    fun getUser(userId: Int) : LiveData<User>
}