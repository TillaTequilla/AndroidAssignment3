package com.example.androidAssignment3.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.androidAssignment3.R
import com.example.androidAssignment3.constance.MAIN
import com.example.androidAssignment3.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenerInitialization()
    }

    private fun listenerInitialization() {
        with(binding) {
            tietPassword.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 5) {
                    tilPassword.error = getString(R.string.login_error_password_few_symbols)
                } else if (!text.contains("[0-9]".toRegex())) {
                    tilPassword.error = getString(R.string.login_error_password_number)
                } else tilPassword.error = null
            }

            tietEmail.doOnTextChanged { text, start, before, count ->
                if (!text!!.contains(".+\\..+@+[A-Za-z]+\\.[A-Za-z]+".toRegex())
                    || text.contains(" ")
                ) {
                    tilEmail.error = getString(R.string.login_error_email_valid_email)
                } else tilEmail.error = null
            }

            bRegister.setOnClickListener {
                val direction = AuthFragmentDirections.actionAuthFragment3ToProfileFragment()///ось тут
                MAIN.navControler.navigate(R.id.action_authFragment3_to_profileFragment)
            }
        }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AuthFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AuthFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
    }
}