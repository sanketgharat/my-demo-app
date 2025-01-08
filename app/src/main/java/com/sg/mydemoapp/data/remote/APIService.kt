package com.sg.mydemoapp.data.remote

import com.sg.mydemoapp.data.model.User
import okhttp3.Call
import retrofit2.Response
import retrofit2.http.GET


interface APIService {

    //https://jsonplaceholder.typicode.com/users
    @GET("users/")
    suspend fun listUsers(): Response<List<User>>
}