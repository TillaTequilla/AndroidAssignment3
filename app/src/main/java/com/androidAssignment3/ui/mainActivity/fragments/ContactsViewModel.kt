package com.androidAssignment3.ui.mainActivity.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidAssignment3.model.Contact
import com.androidAssignment3.util.UsersList

class ContactsViewModel : ViewModel() {

    private val _contactList: MutableLiveData<List<Contact>> = MutableLiveData()

    val contactList: LiveData<List<Contact>> = _contactList

    init {
        _contactList.value = UsersList.getUsers()
    }

    fun deleteContact(index: Int) {
        deleteContact(_contactList.value!![index])
    }

    fun deleteContact(contact: Contact) {
        _contactList.value = _contactList.value?.minus(contact)
    }

    fun addContact(contact: Contact) {
        _contactList.value = _contactList.value?.plus(contact)
    }
}