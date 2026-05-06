package com.youssefmabrouk.heritagequest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.youssefmabrouk.heritagequest.data.CategoryId
import com.youssefmabrouk.heritagequest.data.QuizCategory
import com.youssefmabrouk.heritagequest.ui.components.AdaptivePage
import com.youssefmabrouk.heritagequest.ui.components.InfoCard
import com.youssefmabrouk.heritagequest.ui.components.ResourcePhoto
import com.youssefmabrouk.heritagequest.ui.components.ScreenHeader
import com.youssefmabrouk.heritagequest.ui.theme.DeepBlue
import com.youssefmabrouk.heritagequest.viewmodel.QuizUiState

@Composable
fun CategorySelectionScreen(
    state: QuizUiState,
    onBack: () -> Unit,
    onCategorySelected: (CategoryId) -> Unit
) {
    AdaptivePage {
        ScreenHeader(
            title = "Choose Category",
            onBack = onBack
        )

        BoxWithConstraints {
            if (maxWidth < 620.dp) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    state.categories.forEach { category ->
                        CategoryCard(category, onCategorySelected)
                    }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    state.categories.chunked(2).forEach { rowCategories ->
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            rowCategories.forEach { category ->
                                CategoryCard(
                                    category = category,
                                    onCategorySelected = onCategorySelected,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            if (rowCategories.size == 1) {
                                Column(modifier = Modifier.weight(1f)) {}
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: QuizCategory,
    onCategorySelected: (CategoryId) -> Unit,
    modifier: Modifier = Modifier
) {
    InfoCard(
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (category.isPlayable) 1f else 0.62f)
            .clickable(enabled = category.isPlayable) { onCategorySelected(category.id) }
    ) {
        ResourcePhoto(
            imageResId = category.imageResId,
            contentDescription = category.title
        )
        Text(
            text = category.title,
            style = MaterialTheme.typography.titleLarge,
            color = DeepBlue
        )
        Text(text = category.subtitle)
    }
}
