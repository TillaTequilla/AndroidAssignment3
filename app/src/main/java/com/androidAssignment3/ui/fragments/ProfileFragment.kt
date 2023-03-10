package com.androidAssignment3.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.androidAssignment3.architecture.BaseFragment
import com.androidAssignment3.databinding.FragmentProfileBinding
import com.androidAssignment3.ui.MainActivity


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            tvName.text = (activity as MainActivity).getData()
            btnContacts.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToContactsFragment())
            }
        }

    }
}