package com.example.androidAssignment3.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidAssignment3.DialogFragmentAddContact
import com.example.androidAssignment3.DialogFragmentShowContact
import com.example.androidAssignment3.R
import com.example.androidAssignment3.adapter.ContactController
import com.example.androidAssignment3.adapter.ContactsRecycleViewAdapter
import com.example.androidAssignment3.constance.Constance.ADD_CONTACT_RESULT_KET
import com.example.androidAssignment3.contacts.Contact
import com.example.androidAssignment3.contacts.ContactsViewModel
import com.example.androidAssignment3.databinding.FragmentContactsBinding
import com.example.androidAssignment3.util.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar




class ContactsFragment : Fragment(), ContactController {

    lateinit var binding: FragmentContactsBinding

    private val contactViewModel: ContactsViewModel by viewModels()

    private val adapter: ContactsRecycleViewAdapter by lazy {
        ContactsRecycleViewAdapter(contactController = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentListener()
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter


        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteUser(viewHolder.absoluteAdapterPosition)
            }
        }


        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)


        binding.tvAddContact.setOnClickListener {
            val dialog = DialogFragmentAddContact()
            dialog.show(parentFragmentManager, "addContact")
        }

        binding.ivContactBack.setOnClickListener {
            findNavController().popBackStack()
        }

        contactViewModel.contactList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setFragmentListener(){
        setFragmentResultListener(ADD_CONTACT_RESULT_KET){ _, bundle ->
            val result=bundle.getSerializable("contact")
            addContact(result as Contact)
        }
    }

    private fun addContact(contact: Contact) {
        contactViewModel.addContact(contact)
    }

    fun deleteUser(index: Int) {
        val contact = contactViewModel.contactList.value?.get(index)!!
        contactViewModel.deleteContact(index)
        undoUserDeletion(binding.root, contact)
    }

    override fun deleteUser(contact: Contact) {
        contactViewModel.deleteContact(contact)
        undoUserDeletion(binding.root, contact)
    }

    override fun showContact(contact: Contact) {
        val dialog = DialogFragmentShowContact()
        val args = Bundle()
        args.putSerializable("contact",contact)
        dialog.arguments=args
        dialog.show(parentFragmentManager, "showContact")
    }

    private fun undoUserDeletion(view: View, contact: Contact?) {
        val delMessage = Snackbar.make(
            view,
            getString(R.string.contacts_sbRemoved, contact!!.name),
            Snackbar.LENGTH_LONG
        )
        delMessage.setAction("Cancel") {
            contactViewModel.addContact(contact)
        }.show()
    }

}