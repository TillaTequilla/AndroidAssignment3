package com.androidAssignment3.ui.mainActivity.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.androidAssignment3.architecture.BaseFragment
import com.androidAssignment3.databinding.FragmentProfileBinding
<<<<<<< HEAD:app/src/main/java/com/androidAssignment3/ui/mainActivity/fragments/ProfileFragment.kt
import com.androidAssignment3.ui.mainActivity.MainActivity
=======
import com.androidAssignment3.ui.MainActivity
<<<<<<< Updated upstream:app/src/main/java/com/androidAssignment3/ui/fragments/ProfileFragment.kt
=======
>>>>>>> 54abc7d4bc9fd68b1986da275aa500e1ffccd699:app/src/main/java/com/androidAssignment3/ui/fragments/ProfileFragment.kt
>>>>>>> Stashed changes:app/src/main/java/com/androidAssignment3/ui/mainActivity/fragments/ProfileFragment.kt
import com.androidAssignment3.util.Constance


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            tvName.text = (activity as MainActivity).intent.getStringExtra(Constance.INTENT_NAME)
            btnContacts.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToContactsFragment())
            }
        }
    }
}