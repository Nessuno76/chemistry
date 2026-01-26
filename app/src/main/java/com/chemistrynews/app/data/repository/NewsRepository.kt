package com.chemistrynews.app.data.repository

import com.chemistrynews.app.data.local.ArticleDao
import com.chemistrynews.app.data.model.Article
import com.chemistrynews.app.data.model.ChemistryCategory
import com.chemistrynews.app.data.model.toArticle
import com.chemistrynews.app.data.remote.NewsApiService
import com.chemistrynews.app.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository(private val articleDao: ArticleDao) {

    private val newsApiService = RetrofitClient.newsApiService

    suspend fun searchChemistryNews(
        categories: List<ChemistryCategory> = ChemistryCategory.values().toList()
    ): Flow<Result<List<Article>>> = flow {
        try {
            // Query migliorata con più termini scientifici
            val baseTerms = listOf(
                "chemistry", "chemical", "molecule", "compound", "reaction",
                "synthesis", "research", "discovery", "scientist", "laboratory"
            )
            
            val keywords = if (categories.isEmpty()) {
                baseTerms.joinToString(" OR ")
            } else {
                val categoryKeywords = categories.flatMap { it.keywords }
                (baseTerms + categoryKeywords).distinct().joinToString(" OR ")
            }

            val response = newsApiService.searchNews(
                query = keywords,
                language = "en",
                sortBy = "publishedAt",
                pageSize = 100,
                apiKey = NewsApiService.API_KEY
            )

            if (response.status == "ok") {
                val articles = response.articles
                    .filter { article ->
                        // Filtra articoli rilevanti
                        val text = "${article.title} ${article.description ?: ""}".lowercase()
                        val hasChemistryTerms = baseTerms.any { term -> 
                            text.contains(term.lowercase()) 
                        }
                        val hasValidContent = !article.title.isNullOrBlank() && 
                                            !article.description.isNullOrBlank()
                        hasChemistryTerms && hasValidContent
                    }
                    .map { newsArticle ->
                        val articleCategories = detectCategories(newsArticle.title, newsArticle.description)
                        newsArticle.toArticle(articleCategories)
                    }
                    .distinctBy { it.url }  // Rimuovi duplicati
                
                emit(Result.success(articles))
            } else {
                emit(Result.failure(Exception("Failed to fetch news")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    private fun detectCategories(title: String?, description: String?): List<ChemistryCategory> {
        val text = "${title ?: ""} ${description ?: ""}".lowercase()
        return ChemistryCategory.values().filter { category ->
            category.keywords.any { keyword ->
                text.contains(keyword.lowercase())
            }
        }
    }

    fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getSavedArticles()
    }

    fun getArticlesByCategory(categories: List<ChemistryCategory>): Flow<List<Article>> {
        return articleDao.getArticlesByCategories(categories.map { it.name })
    }

    suspend fun saveArticle(article: Article) {
        articleDao.insertArticle(
            article.copy(
                isSaved = true,
                savedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }

    suspend fun updateArticleWithAISummary(articleId: Long, summary: String) {
        articleDao.updateAISummary(articleId, summary)
    }

    suspend fun getArticleById(id: Long): Article? {
        return articleDao.getArticleById(id)
    }
}
