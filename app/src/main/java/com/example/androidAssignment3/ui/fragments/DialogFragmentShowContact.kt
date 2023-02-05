package com.example.androidAssignment3.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.androidAssignment3.R
import com.example.androidAssignment3.architecture.BaseDialogFragment
import com.example.androidAssignment3.databinding.FragmentShowContactBinding
import com.example.androidAssignment3.extension.setSizePercent
import com.example.androidAssignment3.ui.Contact
import com.example.androidAssignment3.ui.ContactsViewModel
import com.example.androidAssignment3.util.Constance

class DialogFragmentShowContact :
    BaseDialogFragment<FragmentShowContactBinding>(FragmentShowContactBinding::inflate) {

    private val contactViewModel: ContactsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSizePercent(82, 50)
        val contact = arguments?.getSerializable(Constance.CONTACT_SERIALIZABLE) as Contact
        putDataToUi(contact)

    }

    private fun putDataToUi(contact: Contact) {
        contact.apply {
            binding.run {
                tvShowContactName.text = getString(R.string.showContact_name, contact.name)
                if (contact.imageURL != "null") {
                    Glide.with(ivShowContactPhoto).load(contact.imageURL).circleCrop()
                        .into(ivShowContactPhoto)
                } else {
                    ivShowContactPhoto.setImageResource(R.drawable.icon_default_photo)
                }
                ivShowContactTrash?.setOnClickListener {
                    contactViewModel.deleteContact(contact)
                    dismiss()
                }
                tvShowContactCareer.text =
                    getString(R.string.showContact_career, contact.career)
                tvShowContactEmail.text = getString(R.string.showContact_email, contact.eMail)
                tvShowContactPhone.text = getString(R.string.showContact_phone, contact.phone)
                tvShowContactAdress.text =
                    getString(R.string.showContact_address, contact.address)
                tvShowContactBirth.text = getString(R.string.showContact_birth, contact.birth)
            }
        }

    }
}



