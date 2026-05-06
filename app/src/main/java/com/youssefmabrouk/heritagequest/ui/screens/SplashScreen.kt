package com.youssefmabrouk.heritagequest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youssefmabrouk.heritagequest.ui.theme.DeepBlue
import com.youssefmabrouk.heritagequest.ui.theme.MediterraneanBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(900)
        onFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "Tunisia\nHeritage Quest",
                style = MaterialTheme.typography.displaySmall,
                color = DeepBlue,
                textAlign = TextAlign.Center
            )
            Text(
                text = "YoussefMabrouk",
                style = MaterialTheme.typography.titleLarge,
                color = MediterraneanBlue
            )
            CircularProgressIndicator(color = MediterraneanBlue)
        }
    }
}
