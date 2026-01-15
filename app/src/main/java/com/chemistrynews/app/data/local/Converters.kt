package com.chemistrynews.app.data.local

import androidx.room.TypeConverter
import com.chemistrynews.app.data.model.ChemistryCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCategoryList(categories: List<ChemistryCategory>): String {
        return gson.toJson(categories)
    }

    @TypeConverter
    fun toCategoryList(categoriesString: String): List<ChemistryCategory> {
        val type = object : TypeToken<List<ChemistryCategory>>() {}.type
        return gson.fromJson(categoriesString, type)
    }
}
