package com.youssefmabrouk.heritagequest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youssefmabrouk.heritagequest.R
import com.youssefmabrouk.heritagequest.ui.components.AdaptivePage
import com.youssefmabrouk.heritagequest.ui.components.PrimaryActionButton
import com.youssefmabrouk.heritagequest.ui.components.ResourcePhoto
import com.youssefmabrouk.heritagequest.ui.components.StatTile
import com.youssefmabrouk.heritagequest.ui.theme.DeepBlue

@Composable
fun MainMenuScreen(
    onStart: () -> Unit
) {
    AdaptivePage {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = "Tunisia Heritage Quest",
                style = MaterialTheme.typography.displaySmall,
                color = DeepBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Identify modern landmarks and natural treasures from elegant visual clues.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        ResourcePhoto(
            imageResId = R.drawable.photo_bourguiba,
            contentDescription = "Habib Bourguiba Mausoleum"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatTile("Questions", "12", Modifier.weight(1f))
            StatTile("Categories", "6", Modifier.weight(1f))
            StatTile("Points", "+10", Modifier.weight(1f))
        }

        PrimaryActionButton(
            text = "Start Quiz",
            onClick = onStart,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
