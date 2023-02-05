package com.example.androidAssignment3.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResult
import com.example.androidAssignment3.R
import com.example.androidAssignment3.architecture.BaseDialogFragment
import com.example.androidAssignment3.util.Constance.ADD_CONTACT_RESULT_KEY
import com.example.androidAssignment3.databinding.AddContactBinding
import com.example.androidAssignment3.extension.setSizePercent
import com.example.androidAssignment3.ui.Contact
import com.example.androidAssignment3.util.Constance


class DialogFragmentAddContact : BaseDialogFragment<AddContactBinding>(AddContactBinding::inflate) {

    private var imageUri: Uri? = null
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSizePercent(90, 90)
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

        binding.btnSaveContact.setOnClickListener {
            if (binding.etUsernameNew.text!!.isEmpty()) {
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
        val bundle = Bundle()
        bundle.putSerializable(Constance.CONTACT_SERIALIZABLE, contact)
        setFragmentResult(ADD_CONTACT_RESULT_KEY, bundle)
    }

    private fun createContact(): Contact {
        binding.run {
            return Contact(
                imageUri.toString(),
                etUsernameNew.text.toString(),
                etCareerNew.text.toString(),
                etEmailNew.text.toString(),
                etPhoneNew.text.toString(),
                etAddressNew.text.toString(),
                etDateOfBirthNew.text.toString()
            )
        }
    }
}