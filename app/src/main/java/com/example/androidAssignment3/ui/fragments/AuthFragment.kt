package com.example.androidAssignment3.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidAssignment3.R
import com.example.androidAssignment3.architecture.BaseFragment
import com.example.androidAssignment3.util.Constance
import com.example.androidAssignment3.databinding.FragmentAuthBinding
import com.example.androidAssignment3.util.NameParser


class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: AuthFragmentViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.sharedPreferences = this.requireActivity()
            .getSharedPreferences(Constance.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
        sharedPreferences=viewModel.sharedPreferences
        super.onViewCreated(view, savedInstanceState)
        getPreferencesData()
        listenerInitialization()
    }

    private fun getPreferencesData() {
        with(binding) {
            viewModel.apply {
                if (
                    getValueFromSharedPreferences(Constance.SHARED_PREFERENCES_REMEMBER, false)) {
                    etEmail.setText(
                        getValueFromSharedPreferences(
                            Constance.SHARED_PREFERENCES_EMAIL,
                            ""
                        )
                    )
                    etPassword.setText(
                        getValueFromSharedPreferences(
                            Constance.SHARED_PREFERENCES_PASSWORD,
                            ""
                        )
                    )
                    cbRememberMe.isChecked = true
                }
            }
        }
    }

    private fun listenerInitialization() {
        with(binding) {
            etPassword.doAfterTextChanged { text ->
                if (text!!.length < 5) {
                    tilPassword.error = getString(R.string.login_error_password_few_symbols)
                } else if (!text.contains("\\d".toRegex())) {
                    tilPassword.error = getString(R.string.login_error_password_number)
                } else tilPassword.error = null
            }

            etEmail.doAfterTextChanged { text ->
                if (NameParser.validEmail(text.toString())) {
                    tilEmail.error = null
                } else tilEmail.error = getString(R.string.login_error_email_valid_email)
            }

            bRegister.setOnClickListener {
                if (checkForInput()) {
                    if (cbRememberMe.isChecked) {
                        rememberInformation()
                    } else sharedPreferences.edit().clear().apply()
                    val direction = AuthFragmentDirections.actionAuthFragmentToProfileFragment(
                        NameParser.getName(etEmail.text.toString())
                    )
                    findNavController().navigate(direction)
                }
            }
        }
    }

    private fun checkForInput(): Boolean {
        with(binding) {
            return tilEmail.error == null && tilPassword.error == null
                    && etEmail.text!!.isNotEmpty() && etPassword.text!!.isNotEmpty()
        }
    }

    private fun rememberInformation() {
        val checked = binding.cbRememberMe.isChecked
        viewModel.apply {
            putValueToSharedPreferences(
                Constance.SHARED_PREFERENCES_EMAIL,
                binding.etEmail.text.toString()
            )
            putValueToSharedPreferences(
                Constance.SHARED_PREFERENCES_PASSWORD,
                binding.etPassword.text.toString()
            )
            putValueToSharedPreferences(Constance.SHARED_PREFERENCES_REMEMBER, checked)
        }
    }
}