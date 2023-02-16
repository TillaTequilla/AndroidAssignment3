package com.androidAssignment3.ui.mainActivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidAssignment3.model.Contact
import com.androidAssignment3.R
import com.androidAssignment3.databinding.RecycleviewContactItemBinding
import com.androidAssignment3.extension.addCircleImage
import com.androidAssignment3.util.DiffUtil


interface ContactClickListener {
    fun onDeleteClick(contact: Contact)
    fun onItemClick(contact: Contact)
}

class ContactsRecycleViewAdapter(private val contactClickListener: ContactClickListener) :
    ListAdapter<Contact, ContactsRecycleViewAdapter.Holder>(DiffUtil) {


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

    inner class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecycleviewContactItemBinding.bind(item)
        fun bind(contact: Contact) = with(binding)
        {
            with(contact) {
                tvContactName.text = name
                tvContactCareer.text = career
                if (imageURL == "null") {
                    ivContactPhoto.setImageResource(R.drawable.icon_default_photo)
                } else {
                    ivContactPhoto.addCircleImage(imageURL)
                }
                IvRemoveContact.setOnClickListener {
                    contactClickListener.onDeleteClick(this)
                }
                itemView.setOnClickListener {
                    contactClickListener.onItemClick(this)
                }
            }

        }
    }


}
