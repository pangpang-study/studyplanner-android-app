package com.example.myapplication.model

import android.content.Context
import android.content.SharedPreferences

class TokenSave(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(
        "TOKEN", Context.MODE_PRIVATE
    )

    companion object {
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    fun saveToken(accessToken: String, refreshToken: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN,accessToken)
        editor.putString(REFRESH_TOKEN,refreshToken)
        editor.apply()
    }

    fun fetchAccessToken():String?{
        return prefs.getString(ACCESS_TOKEN,null)
    }
    fun fetchRefreshToken():String?{
        return prefs.getString(REFRESH_TOKEN,null)
    }
}