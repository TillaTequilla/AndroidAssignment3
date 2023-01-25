package com.example.androidAssignment3


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidAssignment3.adapter.ContactsRecycleViewAdapter
import com.example.androidAssignment3.databinding.ActivityContactsBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.androidAssignment3.contacts.Contact
import com.example.androidAssignment3.contacts.ContactsViewModel
import com.example.androidAssignment3.util.SwipeToDeleteCallback

class ContactsActivity : BaseActivity<ActivityContactsBinding>(ActivityContactsBinding::inflate) {


    private val contactViewModel: ContactsViewModel by viewModels()
    lateinit var adapter: ContactsRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        adapter = ContactsRecycleViewAdapter(contactViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        contactViewModel.contactList.observe(this) {
            adapter.refresh(it)
        }


        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val holder = viewHolder as ContactsRecycleViewAdapter.Holder
                holder.binding.IvRemoveContact.performClick()
            }
        }


        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)


        binding.tvAddContact.setOnClickListener {
            val dialog = DialogFragmentAddContact()
            dialog.show(supportFragmentManager, "addContact")
        }
    }

    fun addContact(contact: Contact) {
        contactViewModel.contactList.value?.add(contact)
        contactViewModel.contactList.value?.let { adapter.refresh(it) }
    }


}