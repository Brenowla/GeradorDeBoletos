package com.example.geradorboletos.session

import android.content.Context
import android.content.SharedPreferences
import com.example.geradorboletos.R
import javax.inject.Inject

class SessionManager @Inject constructor(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val AUTHORIZATION_TOKEN = "AuthToken"
        const val CUSTOMER = "Customer"
    }

    fun saveAuthToken(token: String) {
        prefs.edit().apply(){
            putString(AUTHORIZATION_TOKEN, token)
            apply()
        }
    }

    fun deleteAuthToken(){
        prefs.edit().remove(AUTHORIZATION_TOKEN).commit()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(AUTHORIZATION_TOKEN, null)
    }
}