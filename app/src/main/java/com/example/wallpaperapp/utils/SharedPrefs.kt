package com.example.wallpaperapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SharedPrefs(context: Context) {

    private val sharedPrefFile = "SharedPrefs"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)


    fun saveSharedPrefs(key:String, value:ArrayList<String>){
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        val gson = Gson()
        val jsonText = gson.toJson(value)
        editor.putString(key,jsonText)
        editor.apply()
    }


    fun getSharedPrefs(key: String): ArrayList<String> {
        val gson = Gson()
        val jsonText = sharedPreferences.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(jsonText, type)

    }

}