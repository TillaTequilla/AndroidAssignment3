package com.androidAssignment3.ui.mainActivity.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResult
import com.androidAssignment3.R
import com.androidAssignment3.architecture.BaseDialogFragment
import com.androidAssignment3.util.Constance.ADD_CONTACT_RESULT_KEY
import com.androidAssignment3.databinding.AddContactBinding
import com.androidAssignment3.extension.setSizePercent
import com.androidAssignment3.extension.toast
import com.androidAssignment3.model.Contact
import com.androidAssignment3.util.Constance


class DialogFragmentAddContact : BaseDialogFragment<AddContactBinding>(AddContactBinding::inflate) {

    private var imageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSizePercent(90, 90)
        listenerInitialization()
    }

    private fun listenerInitialization() {
        val launcher = createLauncher()

        binding.ivAddContactChoosePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            launcher.launch(intent)
        }

        binding.btnSaveContact.setOnClickListener {
            if (binding.etUsernameNew.text!!.isEmpty()) {
                context?.toast(getString(R.string.contacts_toast_noInformation))
            } else {
                val contact = createContact()
                addContactToActivity(contact)
                dismiss()
            }

        }
    }

    private fun createLauncher(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUri = result.data?.data
                binding.ivAddContactPhoto.setImageURI(imageUri)
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