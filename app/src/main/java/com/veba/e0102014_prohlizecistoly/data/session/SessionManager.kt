package com.veba.e0102014_prohlizecistoly.data.session

import android.content.SharedPreferences
import com.veba.e0102014_prohlizecistoly.domain.session.SessionService
import com.veba.e0102014_prohlizecistoly.utility.Constants
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SessionService {

    private val editor = sharedPreferences.edit()

    override fun saveUsername(username: String) {
        editor.putString(Constants.SharedPreferences.PERSON_NAME, username).apply()
    }

    override fun getUsername(): String? {
        return sharedPreferences.getString(Constants.SharedPreferences.PERSON_NAME, "")
    }

    override fun savePersonId(personId: String) {
        editor.putString(Constants.SharedPreferences.PERSON_ID, personId).apply()
    }

    override fun getPersonId(): String? {
        return sharedPreferences.getString(Constants.SharedPreferences.PERSON_ID, "")
    }

    override fun saveApiKey(apiKey: String) {
        editor.putString(Constants.SharedPreferences.API_KEY, apiKey).apply()
    }

    override fun getApiKey(): String? {
        return sharedPreferences.getString(Constants.SharedPreferences.API_KEY, "")
    }

    override fun saveAndroidId(androidKey: String) {
        editor.putString(Constants.SharedPreferences.ANDROID_ID, androidKey).apply()
    }

    override fun getAndroidId(): String? {
        return sharedPreferences.getString(Constants.SharedPreferences.ANDROID_ID, "")
    }

    override fun saveLoginStatus(isLoggedIn: Boolean) {
        editor.putBoolean(Constants.SharedPreferences.LOGIN_STATUS, isLoggedIn).apply()
    }

    override fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(Constants.SharedPreferences.LOGIN_STATUS, false)
    }
}