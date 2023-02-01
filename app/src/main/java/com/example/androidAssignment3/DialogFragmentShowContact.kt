package com.example.androidAssignment3

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.androidAssignment3.contacts.Contact
import com.example.androidAssignment3.databinding.AddContactBinding
import com.example.androidAssignment3.databinding.FragmentShowContactBinding
import com.example.androidAssignment3.extension.setSizePercent
import kotlin.math.max

class DialogFragmentShowContact : DialogFragment() {

    private lateinit var binding: FragmentShowContactBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowContactBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSizePercent(80, 50)
        val contact = arguments?.getSerializable("contact")
        (contact as Contact).apply {
            binding.tvShowContactName.text=getString(R.string.showContact_name, contact.name)
            if(contact.imageURL!="null"){
                Glide.with(binding.ivShowContactPhoto).load(contact.imageURL).circleCrop().into(binding.ivShowContactPhoto)
            } else{
                binding.ivShowContactPhoto.setImageResource(R.drawable.icon_default_photo)
            }
            binding.tvShowContactCareer.text=getString(R.string.showContact_career, contact.career)
            binding.tvShowContactEmail.text=getString(R.string.showContact_email, contact.eMail)
            binding.tvShowContactPhone.text=getString(R.string.showContact_phone, contact.phone)
            binding.tvShowContactAdress.text=getString(R.string.showContact_address, contact.address)
            binding.tvShowContactBirth.text=getString(R.string.showContact_birth, contact.birth)
        }
    }



}