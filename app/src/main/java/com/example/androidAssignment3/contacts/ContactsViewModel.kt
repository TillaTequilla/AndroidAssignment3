package com.example.androidAssignment3.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {

    val contactList: MutableLiveData<ArrayList<Contact>> = MutableLiveData()

    init {
        contactList.value = UsersList.getUsers()
    }

    fun getListUsers() = contactList.value
}