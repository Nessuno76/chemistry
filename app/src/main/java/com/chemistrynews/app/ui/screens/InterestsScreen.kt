package com.chemistrynews.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chemistrynews.app.data.model.ChemistryCategory

@Composable
fun InterestsScreen(
    selectedCategories: Set<ChemistryCategory>,
    onCategoryToggle: (ChemistryCategory) -> Unit,
    onSearchByInterests: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Seleziona i tuoi interessi",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Scegli le aree della chimica che ti interessano di più",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(ChemistryCategory.values().toList()) { category ->
                CategoryCard(
                    category = category,
                    isSelected = selectedCategories.contains(category),
                    onToggle = { onCategoryToggle(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSearchByInterests,
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedCategories.isNotEmpty()
        ) {
            Text("Cerca Articoli (${selectedCategories.size} categorie)")
        }
    }
}

@Composable
fun CategoryCard(
    category: ChemistryCategory,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onToggle,
        label = {
            Text(
                text = category.displayName,
                modifier = Modifier.padding(8.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    )
}
