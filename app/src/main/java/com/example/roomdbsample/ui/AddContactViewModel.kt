package com.example.roomdbsample.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbsample.model.Contact
import com.example.roomdbsample.model.ContactsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 15,Feb,2023
 */
@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val db: ContactsDatabase
) : ViewModel() {
//    var contacts by mutableStateOf(emptyList<Contact>())
//        private set


    fun addContact(contact: Contact) {
        viewModelScope.launch {
//            val contact = Contact(name = "Taha", phone = "09328382893")
//              val contact=Contact(name=title, phone = des)
            db.dao.addContact(contact)
        }
    }
//    fun delContacts(contact:Contact) {
//        viewModelScope.launch {
//            db.dao.delContacts(contact)
//        }
//    }

}