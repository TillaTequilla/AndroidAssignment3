package com.androidAssignment3.util


import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    companion object {
        private const val APP_PREFERENCES = "app_preferences"
    }

    var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}