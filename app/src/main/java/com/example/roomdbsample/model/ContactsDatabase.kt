package com.example.roomdbsample.model

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 15,Feb,2023
 */
@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactsDatabase: RoomDatabase() {
    abstract val dao: ContactDao
}