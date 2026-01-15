package com.chemistrynews.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chemistrynews.app.data.local.Converters

@Entity(tableName = "articles")
@TypeConverters(Converters::class)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String?,
    val content: String?,
    val url: String,
    val imageUrl: String?,
    val source: String,
    val author: String?,
    val publishedAt: String,
    val categories: List<ChemistryCategory> = emptyList(),
    val isSaved: Boolean = false,
    val aiSummary: String? = null,
    val savedAt: Long? = null
)

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)

data class NewsArticle(
    val source: NewsSource,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class NewsSource(
    val id: String?,
    val name: String
)

fun NewsArticle.toArticle(categories: List<ChemistryCategory> = emptyList()): Article {
    return Article(
        title = title,
        description = description,
        content = content,
        url = url,
        imageUrl = urlToImage,
        source = source.name,
        author = author,
        publishedAt = publishedAt,
        categories = categories
    )
}
