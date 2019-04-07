package com.magine.setting

import android.content.Context

class SortingSetting(context: Context, name: String = "SortingSetting") : Settings(context, name) {

    var ascending: Boolean
        get() {
            return preferences.getBoolean("ascending", true)
        }
        set(value) {
            preferences.edit().putBoolean("ascending", value).apply()
        }

    var sortBy: Int
        get() {
            return preferences.getInt("sortBy", 0)
        }
        set(value) {

            preferences.edit().putInt("sortBy", value).apply()

        }

}
