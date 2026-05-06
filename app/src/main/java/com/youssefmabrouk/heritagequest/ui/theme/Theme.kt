package com.youssefmabrouk.heritagequest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = lightColorScheme(
    primary = MediterraneanBlue,
    onPrimary = Color.White,
    secondary = Clay,
    onSecondary = Color.White,
    tertiary = Olive,
    background = Sand,
    onBackground = Ink,
    surface = SoftWhite,
    onSurface = Ink,
    surfaceVariant = Mist,
    onSurfaceVariant = DeepBlue
)

@Composable
fun HeritageQuestTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}
