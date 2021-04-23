/*
 * Created by Muhamad Syafii
 * Tuesday, 6/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.util.db

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.syafii.movapps.model.User
import com.syafii.movapps.util.constant.USER_DATA
import com.syafii.movapps.util.constant.USER_IS_LOGIN


class SharedPreference(context: Context){
    private val PREF_MODE = 0
    private val PREF_NAME = "mov-app-database"

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(
        PREF_NAME,
        PREF_MODE
    )
    val editor : SharedPreferences.Editor = sharedPreferences.edit()

    fun saveString(key: String, value: String){
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String) : String? {
        return sharedPreferences.getString(key, "")
    }

    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int? {
        return sharedPreferences.getInt(key, 0)
    }

    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clearPreference() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    fun saveLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }

    fun setCurrentUser(user: User?) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(USER_IS_LOGIN, true)
        editor.putString(USER_DATA, Gson().toJson(user))
        editor.apply()
    }

    fun getCurrentUser(): User? {
        val data: String? = sharedPreferences.getString(USER_DATA, "{}")
        return Gson().fromJson(data, User::class.java)
    }

    fun isLogin(): Boolean {
        return sharedPreferences.getBoolean(USER_IS_LOGIN, false)
    }

    fun setLogout() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(USER_IS_LOGIN, false)
        editor.putString(USER_DATA, "")
        editor.apply()
    }
}