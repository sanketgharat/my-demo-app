package com.sg.mydemoapp.data

import androidx.lifecycle.LiveData
import com.sg.mydemoapp.data.local.database.UsersDatabase
import com.sg.mydemoapp.data.local.entity.User
import com.sg.mydemoapp.data.remote.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val apiService: APIService, private val database: UsersDatabase) {

    suspend fun getUsers() = withContext(Dispatchers.IO) {
        return@withContext apiService.listUsers()
    }

    fun getUsersFromLocal() : LiveData<List<User>> {
        return database.userDao().getAllUsers()
    }

    suspend fun insertUsersToLocal(list: List<User>) = withContext(Dispatchers.IO) {
         database.userDao().insertData(list)
    }

}