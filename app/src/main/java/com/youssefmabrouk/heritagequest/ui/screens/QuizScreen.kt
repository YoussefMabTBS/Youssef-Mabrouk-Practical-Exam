package com.youssefmabrouk.heritagequest.ui.screens

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youssefmabrouk.heritagequest.ui.components.AdaptivePage
import com.youssefmabrouk.heritagequest.ui.components.InfoCard
import com.youssefmabrouk.heritagequest.ui.components.PrimaryActionButton
import com.youssefmabrouk.heritagequest.ui.components.ResourcePhoto
import com.youssefmabrouk.heritagequest.ui.components.ScreenHeader
import com.youssefmabrouk.heritagequest.ui.theme.DeepBlue
import com.youssefmabrouk.heritagequest.ui.theme.Mist
import com.youssefmabrouk.heritagequest.ui.theme.Olive
import com.youssefmabrouk.heritagequest.viewmodel.QuizUiState

@Composable
fun QuizScreen(
    state: QuizUiState,
    onBack: () -> Unit,
    onAnswerSelected: (String) -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    val question = state.currentQuestion

    AdaptivePage(modifier = Modifier.testTag("quizScreen")) {
        ScreenHeader(
            title = "Image Quiz",
            subtitle = "${state.completedQuestions}/${state.questions.size} completed  |  Score ${state.score}",
            onBack = onBack
        )

        if (question == null) {
            InfoCard(modifier = Modifier.fillMaxWidth()) {
                Text("No questions available.")
            }
            return@AdaptivePage
        }

        LinearProgressIndicator(
            progress = { state.completedQuestions / state.questions.size.toFloat() },
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            trackColor = Mist
        )

        BoxWithConstraints {
            if (maxWidth < 720.dp) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    QuestionVisual(state)
                    AnswerPanel(state, onAnswerSelected, onNext, onFinish)
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    QuestionVisual(state, modifier = Modifier.weight(1f))
                    AnswerPanel(
                        state = state,
                        onAnswerSelected = onAnswerSelected,
                        onNext = onNext,
                        onFinish = onFinish,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun QuestionVisual(state: QuizUiState, modifier: Modifier = Modifier) {
    val question = state.currentQuestion ?: return
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ResourcePhoto(
            imageResId = question.imageResId,
            contentDescription = question.answer
        )
        InfoCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = question.cityOrLocation,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = question.prompt,
                style = MaterialTheme.typography.titleLarge,
                color = DeepBlue
            )
            if (state.timerEnabled) {
                Text(
                    text = "Time left: ${state.timeLeft}s",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun AnswerPanel(
    state: QuizUiState,
    onAnswerSelected: (String) -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    val question = state.currentQuestion ?: return
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        question.options.forEachIndexed { index, option ->
            val prefix = listOf("A", "B", "C", "D")[index]
            AnswerButton(
                text = "$prefix. $option",
                option = option,
                state = state,
                onAnswerSelected = onAnswerSelected
            )
        }

        state.answerResult?.let { result ->
            InfoCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (result.isCorrect) "Correct! +10 points" else "Incorrect",
                    style = MaterialTheme.typography.titleLarge,
                    color = if (result.isCorrect) Olive else MaterialTheme.colorScheme.error
                )
                Text("Correct answer: ${result.correctAnswer}")
                Text(result.explanation)
            }
            PrimaryActionButton(
                text = if (state.isLastQuestion) "See Results" else "Next Question",
                onClick = if (state.isLastQuestion) onFinish else onNext,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AnswerButton(
    text: String,
    option: String,
    state: QuizUiState,
    onAnswerSelected: (String) -> Unit
) {
    val result = state.answerResult
    val question = state.currentQuestion
    val isCorrectAnswer = question?.answer == option
    val isSelectedWrong = state.selectedOption == option && result?.isCorrect == false
    val context = LocalContext.current
    val vibrator = remember(context) { context.findVibrator() }
    val toneGenerator = remember {
        runCatching {
            ToneGenerator(AudioManager.STREAM_MUSIC, 80)
        }.getOrNull()
    }

    DisposableEffect(toneGenerator) {
        onDispose {
            toneGenerator?.release()
        }
    }

    val containerColor = when {
        result != null && isCorrectAnswer -> Olive.copy(alpha = 0.14f)
        isSelectedWrong -> MaterialTheme.colorScheme.error.copy(alpha = 0.12f)
        else -> Color.Transparent
    }

    val contentColor = when {
        result != null && isCorrectAnswer -> Olive
        isSelectedWrong -> MaterialTheme.colorScheme.error
        else -> DeepBlue
    }

    OutlinedButton(
        onClick = {
            // Correct and wrong answers use different feedback.
            if (state.soundEnabled) {
                val tone = if (isCorrectAnswer) {
                    ToneGenerator.TONE_PROP_ACK
                } else {
                    ToneGenerator.TONE_PROP_NACK
                }
                toneGenerator?.startTone(tone, 180)
            }
            if (state.hapticsEnabled) {
                val duration = if (isCorrectAnswer) {
                    45L
                } else {
                    140L
                }
                vibrator?.vibrateOnce(duration)
            }
            onAnswerSelected(option)
        },
        enabled = result == null,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        )
    ) {
        Text(text = text, modifier = Modifier.fillMaxWidth())
    }
}

private fun Context.findVibrator(): Vibrator? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = getSystemService(VibratorManager::class.java)
        manager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }
}

private fun Vibrator.vibrateOnce(durationMs: Long) {
    if (!hasVibrator()) return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrate(VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        @Suppress("DEPRECATION")
        vibrate(durationMs)
    }
}
