package com.rfcreations.usaphonenumbergenerator.repository

import android.content.SharedPreferences

class UserPreferenceRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : UserPreferenceRepository {


    override fun getIntPref(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    override fun getStringPref(key: String, defValue: String): String? {
        return sharedPreferences.getString(key,defValue)
    }

    override fun getBooleanPref(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    override fun editIntPref(key: String, newValue: Int) {
        sharedPreferences.edit().putInt(key, newValue).apply()
    }

    override fun editStringPref(key: String, newValue: String) {
        sharedPreferences.edit().putString(key, newValue).apply()
    }

    override fun editBooleanPref(key: String, newValue: Boolean) {
        sharedPreferences.edit().putBoolean(key, newValue).apply()
    }
}