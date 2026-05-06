package com.youssefmabrouk.heritagequest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youssefmabrouk.heritagequest.data.Difficulty
import com.youssefmabrouk.heritagequest.ui.components.AdaptivePage
import com.youssefmabrouk.heritagequest.ui.components.InfoCard
import com.youssefmabrouk.heritagequest.ui.components.PrimaryActionButton
import com.youssefmabrouk.heritagequest.ui.components.ScreenHeader
import com.youssefmabrouk.heritagequest.ui.components.SettingRow
import com.youssefmabrouk.heritagequest.ui.theme.DeepBlue
import com.youssefmabrouk.heritagequest.viewmodel.QuizUiState

@Composable
fun DifficultyScreen(
    state: QuizUiState,
    onBack: () -> Unit,
    onDifficultySelected: (Difficulty) -> Unit,
    onToggleTimer: () -> Unit,
    onToggleSound: () -> Unit,
    onToggleHaptics: () -> Unit,
    onPlay: () -> Unit
) {
    AdaptivePage {
        ScreenHeader(
            title = "Difficulty & Settings",
            subtitle = "Choose pace and feedback before starting.",
            onBack = onBack
        )

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Difficulty.entries.forEach { difficulty: Difficulty ->
                InfoCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDifficultySelected(difficulty) }
                ) {
                    Text(
                        text = difficulty.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = if (state.selectedDifficulty == difficulty) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            DeepBlue
                        }
                    )
                    Text(difficulty.description)
                    Text(
                        text = "${difficulty.secondsPerQuestion} seconds per question",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        InfoCard(modifier = Modifier.fillMaxWidth()) {
            Text("Game options", style = MaterialTheme.typography.titleLarge)
            SettingRow("Timer", state.timerEnabled, onToggleTimer)
            SettingRow("Sound effects", state.soundEnabled, onToggleSound)
            SettingRow("Haptic feedback", state.hapticsEnabled, onToggleHaptics)
        }

        PrimaryActionButton(
            text = "Play ${state.selectedDifficulty.title}",
            onClick = onPlay,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
