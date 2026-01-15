package com.chemistrynews.app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chemistrynews.app.data.local.AppDatabase
import com.chemistrynews.app.data.preferences.UserPreferences
import com.chemistrynews.app.data.repository.NewsRepository

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val database = AppDatabase.getDatabase(context)
        val repository = NewsRepository(database.articleDao())
        val userPreferences = UserPreferences(context)

        return when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(repository, userPreferences) as T
            }
            modelClass.isAssignableFrom(SavedArticlesViewModel::class.java) -> {
                SavedArticlesViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
