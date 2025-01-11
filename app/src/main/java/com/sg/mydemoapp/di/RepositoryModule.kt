package com.sg.mydemoapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sg.mydemoapp.data.UsersRepository
import com.sg.mydemoapp.data.local.database.UsersDatabase
import com.sg.mydemoapp.data.remote.APIService
import com.sg.mydemoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient) : APIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_USERS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(apiService: APIService, database: UsersDatabase) : UsersRepository {
        return UsersRepository(apiService, database)
    }

}