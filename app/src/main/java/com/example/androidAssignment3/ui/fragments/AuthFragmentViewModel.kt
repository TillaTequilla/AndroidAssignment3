package com.example.androidAssignment3.ui.fragments

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class AuthFragmentViewModel : ViewModel() {
    lateinit var sharedPreferences: SharedPreferences

    fun putValueToSharedPreferences(key: String, value: Any?) {
        when (value) {
            is String -> sharedPreferences.edit().putString(key, value).apply()
            is Boolean -> sharedPreferences.edit().putBoolean(key, value).apply()
        }

    }

    inline fun <reified T : Any> getValueFromSharedPreferences(
        key: String,
        defaultValue: T? = null
    ): T =
        when (T::class) {
            String::class -> sharedPreferences.getString(key, defaultValue as String? ?: "") as T
            Boolean::class -> sharedPreferences.getBoolean(
                key,
                defaultValue as? Boolean ?: false
            ) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
}