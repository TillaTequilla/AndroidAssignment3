package com.androidAssignment3.ui

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.androidAssignment3.util.Constance
import com.androidAssignment3.util.PreferenceHelper

class AuthActivityViewModel : ViewModel() {
    lateinit var sharedPreferences: SharedPreferences

    fun putData(eMail: String, password: String, checked: Boolean) {
        PreferenceHelper.putValueToSharedPreferences(
            Constance.SHARED_PREFERENCES_EMAIL,
            eMail
        )
        PreferenceHelper.putValueToSharedPreferences(
            Constance.SHARED_PREFERENCES_PASSWORD,
            password
        )
        PreferenceHelper.putValueToSharedPreferences(
            Constance.SHARED_PREFERENCES_REMEMBER,
            checked
        )
    }

}