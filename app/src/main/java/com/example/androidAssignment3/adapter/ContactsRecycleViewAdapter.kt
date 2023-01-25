package com.example.androidAssignment3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidAssignment3.contacts.Contact
import com.example.androidAssignment3.contacts.ContactsViewModel
import com.example.androidAssignment3.R
import com.example.androidAssignment3.databinding.RecycleviewContactBinding
import com.google.android.material.snackbar.Snackbar


class ContactsRecycleViewAdapter(var listUsers: ContactsViewModel) :
    RecyclerView.Adapter<ContactsRecycleViewAdapter.Holder>() {

    private var contacts = listUsers.getListUsers()!!.toMutableList()

    inner class Holder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = RecycleviewContactBinding.bind(item)
        fun bind(contact: Contact, position: Int) = with(binding) {
            tvContactName.text = contact.name
            tvContactCareer.text = contact.career
            Glide.with(IvContactPhoto).load(contact.imageURL).circleCrop().into(IvContactPhoto)
            IvRemoveContact.setOnClickListener {
                removeItem(position)
            }
        }

        private fun removeItem(position: Int) {
            val contact = contacts[position]
            contacts.remove(contacts[position])
            notifyItemRemoved(position)
            listUsers.contactList.value = contacts as ArrayList<Contact>
            notifyItemRemoved(position)
            undoUserDeletion(contact, itemView, position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_contact, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(contacts[position], position)

    }

    private fun undoUserDeletion(contact: Contact, view: View, position: Int) {
        val delMessage = Snackbar.make(view, "${contact.name} Removed", Snackbar.LENGTH_LONG)
        delMessage.setAction("Cancel") {
            contacts.add(contact)
            notifyItemInserted(position)
            listUsers.contactList.value = contacts as ArrayList<Contact>
            notifyItemInserted(position)
        }
        delMessage.show()
    }

    override fun getItemCount(): Int {
        return contacts.size
    }


    fun refresh(contacts: List<Contact>) {
        this.contacts = contacts as MutableList<Contact>
        notifyDataSetChanged()
    }

}
