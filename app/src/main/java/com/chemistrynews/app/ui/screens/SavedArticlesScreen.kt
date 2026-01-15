package com.chemistrynews.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chemistrynews.app.data.model.Article
import com.chemistrynews.app.ui.components.ArticleCard

@Composable
fun SavedArticlesScreen(
    savedArticles: List<Article>,
    onArticleClick: (Article) -> Unit,
    onDeleteArticle: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Articoli Salvati",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (savedArticles.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nessun articolo salvato",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(savedArticles) { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onArticleClick(article) },
                        onSaveClick = { onDeleteArticle(article) }
                    )
                }
            }
        }
    }
}
