package com.sg.mydemoapp.di

import android.content.Context
import androidx.room.Room
import com.sg.mydemoapp.data.local.database.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context,
            UsersDatabase::class.java, "users_database"
        ).build()
    }
}