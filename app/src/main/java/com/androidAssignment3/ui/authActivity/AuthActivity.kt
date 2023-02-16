package com.androidAssignment3.ui.authActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.androidAssignment3.R
import com.androidAssignment3.architecture.BaseActivity
import com.androidAssignment3.databinding.ActivityAuthBinding
<<<<<<< Updated upstream:app/src/main/java/com/androidAssignment3/ui/AuthActivity.kt
=======
<<<<<<< HEAD:app/src/main/java/com/androidAssignment3/ui/authActivity/AuthActivity.kt
import com.androidAssignment3.ui.mainActivity.MainActivity
=======
>>>>>>> 54abc7d4bc9fd68b1986da275aa500e1ffccd699:app/src/main/java/com/androidAssignment3/ui/AuthActivity.kt
>>>>>>> Stashed changes:app/src/main/java/com/androidAssignment3/ui/authActivity/AuthActivity.kt
import com.androidAssignment3.util.Constance
import com.androidAssignment3.util.NameParser
import com.androidAssignment3.util.PreferenceHelper

class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {
    lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        preferenceHelper = PreferenceHelper(this)
        super.onCreate(savedInstanceState)
        getPreferencesData()
        listenerInitialization()
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

            btnRegister.setOnClickListener {
                if (cbRememberMe.isChecked) {
                    rememberInformation()
                } else preferenceHelper.clear()
                if (checkForInput()) {
                    val name: String = getName()
                    val intent = Intent(this@AuthActivity, MainActivity::class.java)
                    intent.putExtra(Constance.INTENT_NAME, name)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
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

    private fun getPreferencesData() {
        with(binding) {
            if (
                preferenceHelper.getBoolean(
                    Constance.SHARED_PREFERENCES_REMEMBER
                )
            ) {
                etEmail.setText(
                    preferenceHelper.getString(
                        Constance.SHARED_PREFERENCES_EMAIL
                    )
                )
                etPassword.setText(
                    preferenceHelper.getString(
                        Constance.SHARED_PREFERENCES_PASSWORD
                    )
                )
                cbRememberMe.isChecked = true
            }

        }
    }

    private fun rememberInformation() {
<<<<<<< Updated upstream:app/src/main/java/com/androidAssignment3/ui/AuthActivity.kt
=======
<<<<<<< HEAD:app/src/main/java/com/androidAssignment3/ui/authActivity/AuthActivity.kt
        val checked = binding.cbRememberMe.isChecked
        preferenceHelper.putString(
            Constance.SHARED_PREFERENCES_EMAIL,
            binding.etEmail.text.toString()
        )
        preferenceHelper.putString(
            Constance.SHARED_PREFERENCES_PASSWORD,
            binding.etPassword.text.toString()
        )
        preferenceHelper.putBoolean(
            Constance.SHARED_PREFERENCES_REMEMBER,
            checked
        )
=======
>>>>>>> Stashed changes:app/src/main/java/com/androidAssignment3/ui/authActivity/AuthActivity.kt
        binding.run { authActivityViewModel.putData(
            etEmail.text.toString(),
            etPassword.text.toString(),
            cbRememberMe.isChecked
        ) }

>>>>>>> 54abc7d4bc9fd68b1986da275aa500e1ffccd699:app/src/main/java/com/androidAssignment3/ui/AuthActivity.kt
    }


    private fun getName(): String {
        return NameParser.getName(binding.etEmail.text.toString())
    }

}