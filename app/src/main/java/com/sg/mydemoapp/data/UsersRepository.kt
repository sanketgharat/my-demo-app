package com.sg.mydemoapp.data

import com.sg.mydemoapp.data.remote.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val apiService: APIService) {

    suspend fun getUsers() = withContext(Dispatchers.IO) {
        return@withContext apiService.listUsers()
    }

}