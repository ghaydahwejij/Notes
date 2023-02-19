package com.example.roomdbsample.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 15,Feb,2023
 */
@Dao
interface ContactDao {

    @Insert
    suspend fun addContact(contact: Contact)

    @Query("select * from contact")
    fun getContacts(): Flow<List<Contact>>

    @Delete
    suspend fun delContacts(contact:Contact)

    @Query("SELECT (SELECT COUNT(*) FROM contact) == 0")
    fun Empty(): Boolean

}