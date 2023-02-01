package com.example.androidAssignment3.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidAssignment3.contacts.Contact
import com.example.androidAssignment3.contacts.ContactsViewModel
import com.example.androidAssignment3.R
import com.example.androidAssignment3.databinding.RecycleviewContactItemBinding
import com.example.androidAssignment3.util.DiffUtil
import com.google.android.material.snackbar.Snackbar


interface ContactController {
    fun deleteUser(contact: Contact)
    fun showContact(contact: Contact)
}

class ContactsRecycleViewAdapter(private val contactController: ContactController) :
    ListAdapter<Contact, ContactsRecycleViewAdapter.Holder>(DiffUtil) {


    inner class Holder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = RecycleviewContactItemBinding.bind(item)
        fun bind(contact: Contact) = with(binding) {
            tvContactName.text = contact.name
            tvContactCareer.text = contact.career
            if (contact.imageURL == "null") {
                ivContactPhoto.setImageResource(R.drawable.icon_default_photo)
            } else {
                Glide.with(ivContactPhoto).load(contact.imageURL).circleCrop().into(ivContactPhoto)
            }
            IvRemoveContact.setOnClickListener {
                contactController.deleteUser(contact)
            }
            itemView.setOnClickListener {
                contactController.showContact(contact)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_contact_item, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


}
