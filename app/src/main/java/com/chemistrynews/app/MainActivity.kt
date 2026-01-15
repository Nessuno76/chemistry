package com.chemistrynews.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.chemistrynews.app.ui.navigation.NavGraph
import com.chemistrynews.app.ui.theme.ChemistryNewsAppTheme
import com.chemistrynews.app.ui.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChemistryNewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModelFactory = ViewModelFactory(applicationContext)

                    NavGraph(
                        navController = navController,
                        viewModelFactory = viewModelFactory
                    )
                }
            }
        }
    }
}
