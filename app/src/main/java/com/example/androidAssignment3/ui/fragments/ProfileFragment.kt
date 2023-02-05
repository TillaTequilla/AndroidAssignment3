package com.example.androidAssignment3.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidAssignment3.architecture.BaseFragment
import com.example.androidAssignment3.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val args: ProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            tvName.text = args.userName
            btnContacts.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToContactsFragment())
            }
        }

    }
}