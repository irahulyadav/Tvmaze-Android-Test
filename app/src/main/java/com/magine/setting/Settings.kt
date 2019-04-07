package com.magine.setting

import android.content.Context
import android.content.SharedPreferences


open class Settings(context: Context, name: String) {
    val preferences: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)


    fun save(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    var acceptTerms: Boolean
        get() {
            return preferences.getBoolean("acceptTerms", false)
        }
        set(value) {
            preferences.edit().putBoolean("acceptTerms", value).apply()
        }

}