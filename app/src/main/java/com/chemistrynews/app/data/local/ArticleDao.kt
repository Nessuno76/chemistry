package com.chemistrynews.app.data.local

import androidx.room.*
import com.chemistrynews.app.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles WHERE isSaved = 1 ORDER BY savedAt DESC")
    fun getSavedArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Long): Article?

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("DELETE FROM articles WHERE isSaved = 0")
    suspend fun deleteUnsavedArticles()

    @Query("UPDATE articles SET aiSummary = :summary WHERE id = :articleId")
    suspend fun updateAISummary(articleId: Long, summary: String)

    @Query("SELECT * FROM articles WHERE categories LIKE '%' || :category || '%'")
    fun getArticlesByCategory(category: String): Flow<List<Article>>

    fun getArticlesByCategories(categories: List<String>): Flow<List<Article>> {
        // Note: This is a simplified version. For production, consider using a more robust query
        return getAllArticles()
    }
}
