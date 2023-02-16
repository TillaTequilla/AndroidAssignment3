package com.androidAssignment3.ui.mainActivity.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.androidAssignment3.R
import com.androidAssignment3.architecture.BaseDialogFragment
import com.androidAssignment3.databinding.FragmentShowContactBinding
import com.androidAssignment3.extension.addCircleImage
import com.androidAssignment3.extension.setSizePercent
import com.androidAssignment3.model.Contact
import com.androidAssignment3.util.Constance

class DialogFragmentShowContact :
    BaseDialogFragment<FragmentShowContactBinding>(FragmentShowContactBinding::inflate) {

    private val contactViewModel: ContactsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSizePercent(82, 50)
        val contact = arguments?.getSerializable(Constance.CONTACT_SERIALIZABLE) as Contact
        putDataToUi(contact)
        binding.ivShowContactTrash?.setOnClickListener {
            contactViewModel.deleteContact(contact)
            dismiss()
        }

    }

    private fun putDataToUi(contact: Contact) {
        contact.apply {
            binding.run {
                tvShowContactName.text = getString(R.string.showContact_name, contact.name)
                if (contact.imageURL != "null") {
                    ivShowContactPhoto.addCircleImage(imageURL)
                } else {
                    ivShowContactPhoto.setImageResource(R.drawable.icon_default_photo)
                }
                tvShowContactCareer.text =
                    getString(R.string.showContact_career, contact.career)
                tvShowContactEmail.text = getString(R.string.showContact_email, contact.eMail)
                tvShowContactPhone.text = getString(R.string.showContact_phone, contact.phone)
                tvShowContactAddress.text =
                    getString(R.string.showContact_address, contact.address)
                tvShowContactBirth.text = getString(R.string.showContact_birth, contact.birth)
            }
        }

    }
}



