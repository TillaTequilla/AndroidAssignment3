package com.example.androidAssignment3.screens

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.androidAssignment3.R
import com.example.androidAssignment3.constance.Constance
import com.example.androidAssignment3.databinding.FragmentAuthBinding
import com.example.androidAssignment3.util.NameParser


class AuthFragment : Fragment() {

    lateinit var binding: FragmentAuthBinding

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPreferences = this.requireActivity()
            .getSharedPreferences(Constance.SHAREDPREFERENCES_NAME, MODE_PRIVATE)
        super.onViewCreated(view, savedInstanceState)
        getPreferencesData()
        listenerInitialization()
    }

    private fun getPreferencesData() {
        if (sharedPreferences.getBoolean(Constance.SHAREDPREFERENCES_REMEMBER, false)) {
            binding.tietEmail.setText(
                sharedPreferences.getString(
                    Constance.SHAREDPREFERENCES_EMAIL,
                    null
                )
            )
            binding.tietPassword.setText(
                sharedPreferences.getString(
                    Constance.SHAREDPREFERENCES_PASSWORD,
                    null
                )
            )
            binding.cbRememberMe.isChecked = true
        }
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
                if (checkForInput()) {
                    if (cbRememberMe.isChecked) {
                        rememberInformation()
                    } else sharedPreferences.edit().clear().apply()
                    val direction = AuthFragmentDirections.actionAuthFragmentToProfileFragment(
                        NameParser.getName(binding.tietEmail.text.toString())
                    )
                    findNavController().navigate(direction)
                }
            }

        }
    }

    private fun checkForInput(): Boolean {
        with(binding) {
            return tilEmail.error == null && tilPassword.error == null
                    && tietEmail.text!!.isNotEmpty() && tietPassword.text!!.isNotEmpty()
        }
    }

    private fun rememberInformation() {
        val checked = binding.cbRememberMe.isChecked
        val editor = sharedPreferences.edit()
        editor.putString(Constance.SHAREDPREFERENCES_EMAIL, binding.tietEmail.text.toString())
        editor.putString(Constance.SHAREDPREFERENCES_PASSWORD, binding.tietPassword.text.toString())
        editor.putBoolean(Constance.SHAREDPREFERENCES_REMEMBER, checked)
        editor.apply()
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