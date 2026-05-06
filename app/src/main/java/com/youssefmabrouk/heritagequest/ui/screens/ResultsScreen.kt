package com.youssefmabrouk.heritagequest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youssefmabrouk.heritagequest.ui.components.AdaptivePage
import com.youssefmabrouk.heritagequest.ui.components.InfoCard
import com.youssefmabrouk.heritagequest.ui.components.PrimaryActionButton
import com.youssefmabrouk.heritagequest.ui.components.StatTile
import com.youssefmabrouk.heritagequest.ui.theme.DeepBlue
import com.youssefmabrouk.heritagequest.viewmodel.QuizUiState

@Composable
fun ResultsScreen(
    state: QuizUiState,
    onRestart: () -> Unit,
    onHome: () -> Unit
) {
    val message = when {
        state.percentage >= 85 -> "Excellent mastery of Tunisian heritage."
        state.percentage >= 60 -> "Good work. A few sites are worth revisiting."
        else -> "Keep exploring. Heritage knowledge grows with curiosity."
    }

    AdaptivePage(modifier = Modifier.testTag("resultsScreen")) {
        Text(
            text = "Quiz Results",
            style = MaterialTheme.typography.displaySmall,
            color = DeepBlue,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        InfoCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${state.percentage}%",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = message,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatTile("Final score", "${state.score}", Modifier.weight(1f))
            StatTile("Questions", "${state.questions.size}", Modifier.weight(1f))
            StatTile("Difficulty", state.selectedDifficulty.title, Modifier.weight(1f))
        }

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            PrimaryActionButton(
                text = "Play Again",
                onClick = onRestart,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedButton(
                onClick = onHome,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Main Menu")
            }
        }
    }
}
