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
            val keywords = if (categories.isEmpty()) {
                "chemistry"
            } else {
                categories.flatMap { it.keywords }.joinToString(" OR ")
            }

            val response = newsApiService.searchNews(
                query = keywords,
                apiKey = NewsApiService.API_KEY
            )

            if (response.status == "ok") {
                val articles = response.articles.map { newsArticle ->
                    val articleCategories = detectCategories(newsArticle.title, newsArticle.description)
                    newsArticle.toArticle(articleCategories)
                }
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
