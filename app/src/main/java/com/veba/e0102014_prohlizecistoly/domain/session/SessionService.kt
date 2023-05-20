package com.veba.e0102014_prohlizecistoly.domain.session

interface SessionService {
    fun saveUsername(username: String)
    fun getUsername(): String?
    fun savePersonId(personId: String)
    fun getPersonId(): String?
    fun saveApiKey(apiKey: String)
    fun getApiKey(): String?
    fun saveAndroidId(androidKey: String)
    fun getAndroidId(): String?
    fun saveLoginStatus(isLoggedIn: Boolean)
    fun isLoggedIn(): Boolean
}