package com.example.roomdbsample.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 15,Feb,2023
 */
@Entity
@Parcelize
data class Contact(
    val name: String,
    val phone: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

) : Parcelable
