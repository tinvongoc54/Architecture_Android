package com.example.myapplication.data.source.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {
    private val mSharedPrefs: SharedPreferences
    var accessToken: String
        set(value) = put(SharedPrefs.PREF_ACCESS_TOKEN, value)
        get() = get(SharedPrefs.PREF_ACCESS_TOKEN, String::class.java)

    init {
        mSharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> mSharedPrefs.getString(key, "")
            Boolean::class.java -> mSharedPrefs.getBoolean(key, false)
            Float::class.java -> mSharedPrefs.getFloat(key, -1f)
            Double::class.java -> mSharedPrefs.getFloat(key, -1f)
            Int::class.java -> mSharedPrefs.getInt(key, -1)
            Long::class.java -> mSharedPrefs.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = mSharedPrefs.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        mSharedPrefs.edit().clear().apply()
    }

    companion object {
        val PREFS_NAME = "Android Architecture"
        private val PREFIX = "Architecture_"
        val PREF_ACCESS_TOKEN = PREFIX + "access_token"
    }
}