package com.chemistrynews.app.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.chemistrynews.app.data.model.Article
import com.chemistrynews.app.ui.viewmodel.NewsViewModel
import com.chemistrynews.app.ui.viewmodel.SavedArticlesViewModel

enum class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    SEARCH("search", "Cerca", Icons.Filled.Search),
    INTERESTS("interests", "Interessi", Icons.Filled.Category),
    SAVED("saved", "Salvati", Icons.Filled.Bookmark)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    newsViewModel: NewsViewModel,
    savedArticlesViewModel: SavedArticlesViewModel,
    onArticleClick: (Article) -> Unit
) {
    var selectedTab by remember { mutableStateOf(BottomNavItem.SEARCH) }
    val uiState by newsViewModel.uiState.collectAsState()
    val savedArticles by savedArticlesViewModel.savedArticles.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chemistry News") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                BottomNavItem.values().forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedTab == item,
                        onClick = { selectedTab = item }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            BottomNavItem.SEARCH -> {
                SearchScreen(
                    articles = uiState.articles,
                    isLoading = uiState.isLoading,
                    error = uiState.error,
                    onArticleClick = onArticleClick,
                    onSaveArticle = { article ->
                        newsViewModel.saveArticle(article)
                    },
                    onSearch = {
                        newsViewModel.searchNews()
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavItem.INTERESTS -> {
                InterestsScreen(
                    selectedCategories = uiState.selectedCategories,
                    onCategoryToggle = { category ->
                        newsViewModel.toggleCategory(category)
                    },
                    onSearchByInterests = {
                        newsViewModel.searchNews(uiState.selectedCategories.toList())
                        selectedTab = BottomNavItem.SEARCH
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavItem.SAVED -> {
                SavedArticlesScreen(
                    savedArticles = savedArticles,
                    onArticleClick = onArticleClick,
                    onDeleteArticle = { article ->
                        savedArticlesViewModel.deleteArticle(article)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}
