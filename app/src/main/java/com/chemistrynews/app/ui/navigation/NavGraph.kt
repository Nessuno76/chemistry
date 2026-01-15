package com.chemistrynews.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chemistrynews.app.ui.screens.*
import com.chemistrynews.app.ui.viewmodel.NewsViewModel
import com.chemistrynews.app.ui.viewmodel.SavedArticlesViewModel
import com.chemistrynews.app.ui.viewmodel.ViewModelFactory

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object ArticleDetail : Screen("article_detail/{articleId}") {
        fun createRoute(articleId: Long) = "article_detail/$articleId"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModelFactory: ViewModelFactory,
    newsViewModel: NewsViewModel = viewModel(factory = viewModelFactory),
    savedArticlesViewModel: SavedArticlesViewModel = viewModel(factory = viewModelFactory)
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                newsViewModel = newsViewModel,
                savedArticlesViewModel = savedArticlesViewModel,
                onArticleClick = { article ->
                    navController.navigate(Screen.ArticleDetail.createRoute(article.id))
                }
            )
        }

        composable(
            route = Screen.ArticleDetail.route,
            arguments = listOf(
                navArgument("articleId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getLong("articleId") ?: 0
            val uiState by newsViewModel.uiState.collectAsState()
            val article = uiState.articles.find { it.id == articleId }

            article?.let {
                ArticleDetailScreen(
                    article = it,
                    onBack = { navController.popBackStack() },
                    onSaveArticle = { newsViewModel.saveArticle(it) },
                    onGenerateSummary = {
                        it.content?.let { content ->
                            newsViewModel.generateAISummary(it.id, content)
                        }
                    }
                )
            }
        }
    }
}
