package com.sg.mydemoapp.data

import androidx.lifecycle.MutableLiveData
import com.sg.mydemoapp.data.model.User
import com.sg.mydemoapp.data.remote.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val apiService: APIService) {

    public suspend fun getUsers() = withContext(Dispatchers.IO) {
        return@withContext apiService.listUsers()
    }

}