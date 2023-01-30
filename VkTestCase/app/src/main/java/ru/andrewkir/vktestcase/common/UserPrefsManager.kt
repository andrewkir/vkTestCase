package ru.andrewkir.vktestcase.common

import android.content.Context
import android.content.SharedPreferences
import ru.andrewkir.vktestcase.R


class UserPrefsManager(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val prefs: SharedPreferences = applicationContext.getSharedPreferences(
        applicationContext.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
    }

    fun clearUser() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    var refreshToken: String?
        get() = prefs.getString(REFRESH_TOKEN, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(REFRESH_TOKEN, value)
            editor.apply()
        }

    var accessToken: String?
        get() = prefs.getString(ACCESS_TOKEN, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(ACCESS_TOKEN, value)
            editor.apply()
        }

    var username: String?
        get() = prefs.getString(USERNAME, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(USERNAME, value)
            editor.apply()
        }

    var email: String?
        get() = prefs.getString(EMAIL, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(EMAIL, value)
            editor.apply()
        }
}
