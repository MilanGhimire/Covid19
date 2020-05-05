package com.milanghimire.covid19.util

import android.content.Context

class SharedPreferencesUtil() {

    companion object {

        private const val PREF_FILE_NAME = "Covid19"

        fun saveToPreference(context: Context, preferenceName: String, preferenceValue: String) {
            val sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(preferenceName, preferenceValue)
            editor.apply()
        }

        fun removeFromPreference(context: Context, preferenceName: String) {
            val prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.remove(preferenceName)
            editor.apply()

        }

        fun readFromPreference(context: Context, preferenceName: String, defaultValue: String): String{
            val prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            return prefs.getString(preferenceName, defaultValue).toString()
        }
    }
}