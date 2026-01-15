package com.chemistrynews.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chemistrynews.app.data.model.Article
import com.chemistrynews.app.data.model.ChemistryCategory
import com.chemistrynews.app.data.preferences.UserPreferences
import com.chemistrynews.app.data.repository.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class NewsUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategories: Set<ChemistryCategory> = emptySet()
)

class NewsViewModel(
    private val repository: NewsRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    init {
        observeSelectedCategories()
    }

    private fun observeSelectedCategories() {
        viewModelScope.launch {
            userPreferences.selectedCategories.collect { categories ->
                _uiState.update { it.copy(selectedCategories = categories) }
            }
        }
    }

    fun searchNews(categories: List<ChemistryCategory> = emptyList()) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            repository.searchChemistryNews(categories).collect { result ->
                result.onSuccess { articles ->
                    _uiState.update {
                        it.copy(
                            articles = articles,
                            isLoading = false,
                            error = null
                        )
                    }
                }.onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Unknown error"
                        )
                    }
                }
            }
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            repository.saveArticle(article)
        }
    }

    fun toggleCategory(category: ChemistryCategory) {
        viewModelScope.launch {
            userPreferences.toggleCategory(category)
        }
    }

    fun updateSelectedCategories(categories: Set<ChemistryCategory>) {
        viewModelScope.launch {
            userPreferences.updateSelectedCategories(categories)
        }
    }

    fun generateAISummary(articleId: Long, content: String) {
        viewModelScope.launch {
            try {
                // Simulazione di generazione AI summary
                // In produzione, qui si chiamerebbe un'API come Claude o OpenAI
                val summary = generateMockSummary(content)
                repository.updateArticleWithAISummary(articleId, summary)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun generateMockSummary(content: String): String {
        // Mock implementation - replace with actual AI API call
        val preview = content.take(500)
        return """
            Riassunto Automatico:

            Questo articolo tratta argomenti rilevanti nel campo della chimica.
            $preview...

            Punti Chiave:
            • Scoperte recenti nella ricerca chimica
            • Implicazioni pratiche e applicazioni
            • Sviluppi futuri nel settore
        """.trimIndent()
    }
}
