package com.chemistrynews.app.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chemistrynews.app.data.model.ChemistryCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {

    private val SELECTED_CATEGORIES = stringSetPreferencesKey("selected_categories")

    val selectedCategories: Flow<Set<ChemistryCategory>> = context.dataStore.data
        .map { preferences ->
            val categoryNames = preferences[SELECTED_CATEGORIES] ?: emptySet()
            categoryNames.mapNotNull { name ->
                try {
                    ChemistryCategory.valueOf(name)
                } catch (e: IllegalArgumentException) {
                    null
                }
            }.toSet()
        }

    suspend fun updateSelectedCategories(categories: Set<ChemistryCategory>) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CATEGORIES] = categories.map { it.name }.toSet()
        }
    }

    suspend fun toggleCategory(category: ChemistryCategory) {
        context.dataStore.edit { preferences ->
            val current = preferences[SELECTED_CATEGORIES] ?: emptySet()
            val updated = if (current.contains(category.name)) {
                current - category.name
            } else {
                current + category.name
            }
            preferences[SELECTED_CATEGORIES] = updated
        }
    }
}
