package com.ekotech.breakingbad.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun listOfIntToJson(value: List<Int>?): String? {
        return try {
            gson.toJson(value)
        } catch (E: Exception) {
            null
        }
    }

    @TypeConverter
    @JvmStatic
    fun jsonToListOfInts(value: String?): List<Int>? {
        return try {
            val type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(
                value,
                type
            )
        } catch (e: Exception) {
            null
        }
    }

    @TypeConverter
    @JvmStatic
    fun listOfStringToJson(value: List<String>?): String? {
        return try {
            gson.toJson(value)
        } catch (E: Exception) {
            null
        }
    }

    @TypeConverter
    @JvmStatic
    fun jsonToListOfStrings(value: String?): List<String>? {
        return try {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(
                value,
                type
            )
        } catch (e: Exception) {
            null
        }
    }
}