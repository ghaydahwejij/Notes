package com.example.roomdbsample.di

import android.app.Application
import androidx.room.Room
import com.example.roomdbsample.model.ContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 15,Feb,2023
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ContactsDatabase {
        val database = Room.databaseBuilder(
            app,
            ContactsDatabase::class.java,
            "contacts_db"
        ).build()
        return database
    }

}