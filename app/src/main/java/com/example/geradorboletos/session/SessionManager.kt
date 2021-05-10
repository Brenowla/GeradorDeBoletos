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
        val editor = prefs.edit()
        editor.putString(AUTHORIZATION_TOKEN, token)
        editor.apply()
    }

    fun deleteAuthToken(){
        val editor = prefs.edit()
        editor.remove(AUTHORIZATION_TOKEN)
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(AUTHORIZATION_TOKEN, null)
    }

}