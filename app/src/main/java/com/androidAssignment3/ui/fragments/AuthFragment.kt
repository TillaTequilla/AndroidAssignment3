package com.androidAssignment3.ui.fragments


import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.androidAssignment3.architecture.BaseFragment
import com.androidAssignment3.util.Constance
import com.androidAssignment3.util.PreferenceHelper

import com.androidAssignment3.R
import com.androidAssignment3.databinding.FragmentAuthBinding


class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: AuthFragmentViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.sharedPreferences = PreferenceHelper.init(this.requireActivity())

        super.onViewCreated(view, savedInstanceState)
        getPreferencesData()
        listenerInitialization()
    }

    private fun getPreferencesData() {
        with(binding) {
            viewModel.apply {
                if (
                    com.androidAssignment3.util.PreferenceHelper.getValueFromSharedPreferences(com.androidAssignment3.util.Constance.SHARED_PREFERENCES_REMEMBER, false)) {
                    etEmail.setText(
                        com.androidAssignment3.util.PreferenceHelper.getValueFromSharedPreferences(
                            com.androidAssignment3.util.Constance.SHARED_PREFERENCES_EMAIL,
                            ""
                        )
                    )
                    etPassword.setText(
                        com.androidAssignment3.util.PreferenceHelper.getValueFromSharedPreferences(
                            com.androidAssignment3.util.Constance.SHARED_PREFERENCES_PASSWORD,
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
                if (com.androidAssignment3.util.NameParser.validEmail(text.toString())) {
                    tilEmail.error = null
                } else tilEmail.error = getString(R.string.login_error_email_valid_email)
            }

            btnRegister.setOnClickListener {
                if (checkForInput()) {
                    if (cbRememberMe.isChecked) {
                        rememberInformation()
                    } else com.androidAssignment3.util.PreferenceHelper.sharedPreferences.edit().clear().apply()
                    val direction =
                        com.androidAssignment3.ui.fragments.AuthFragmentDirections.actionAuthFragmentToProfileFragment(
                            com.androidAssignment3.util.NameParser.getName(etEmail.text.toString())
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
            PreferenceHelper.putValueToSharedPreferences(
                Constance.SHARED_PREFERENCES_EMAIL,
                binding.etEmail.text.toString()
            )
            PreferenceHelper.putValueToSharedPreferences(
                Constance.SHARED_PREFERENCES_PASSWORD,
                binding.etPassword.text.toString()
            )
            PreferenceHelper.putValueToSharedPreferences(Constance.SHARED_PREFERENCES_REMEMBER, checked)
        }
    }
}