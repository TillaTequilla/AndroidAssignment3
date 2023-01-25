package com.example.androidAssignment3

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.androidAssignment3.databinding.AddContactBinding
import com.example.androidAssignment3.contacts.Contact


class DialogFragmentAddContact : DialogFragment() {

    private var imageUri: Uri? = null
    private lateinit var binding: AddContactBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivAddContactChoosePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            launcher.launch(intent)
        }

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    imageUri = result.data?.data
                    binding.ivAddContactPhoto.setImageURI(imageUri)
                }
            }
        binding.bSaveContact.setOnClickListener {
            if (binding.tiedUsernameNew.text!!.isEmpty()) {
                Toast.makeText(
                    context,
                    getString(R.string.contacts_toast_noInformation),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val contact = createContact()
                addContactToActivity(contact)
                dismiss()
            }

        }
    }

    private fun addContactToActivity(contact: Contact) {
        activity?.let {
            (it as ContactsActivity).apply {
                addContact(contact)
            }
        }
    }

    private fun createContact(): Contact {
        return Contact(
            imageUri.toString(),
            binding.tiedUsernameNew.text.toString(),
            binding.tiedCareerNew.text.toString()
        )
    }
}