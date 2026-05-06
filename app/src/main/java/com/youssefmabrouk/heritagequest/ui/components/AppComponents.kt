package com.youssefmabrouk.heritagequest.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youssefmabrouk.heritagequest.data.IllustrationKind
import com.youssefmabrouk.heritagequest.ui.theme.Clay
import com.youssefmabrouk.heritagequest.ui.theme.DeepBlue
import com.youssefmabrouk.heritagequest.ui.theme.Gold
import com.youssefmabrouk.heritagequest.ui.theme.MediterraneanBlue
import com.youssefmabrouk.heritagequest.ui.theme.Mist
import com.youssefmabrouk.heritagequest.ui.theme.Olive
import com.youssefmabrouk.heritagequest.ui.theme.Sand
import com.youssefmabrouk.heritagequest.ui.theme.SoftWhite

@Composable
fun AdaptivePage(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    // Common page layout used by all main screens.
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 960.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                content = content
            )
        }
    }
}

@Composable
fun ScreenHeader(
    title: String,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (onBack != null) {
            OutlinedButton(
                onClick = onBack,
                shape = RoundedCornerShape(8.dp),
                contentPadding = ButtonDefaults.TextButtonContentPadding
            ) {
                Text("Back")
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = DeepBlue
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MediterraneanBlue)
    ) {
        Text(text = text, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = SoftWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = content
        )
    }
}

@Composable
fun StatTile(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.heightIn(min = 108.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = SoftWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = value,
                style = if (value.length > 4) {
                    MaterialTheme.typography.titleMedium
                } else {
                    MaterialTheme.typography.titleLarge
                },
                color = MediterraneanBlue,
                textAlign = TextAlign.Center,
                maxLines = 1,
                softWrap = false,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = DeepBlue,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ResourcePhoto(
    @DrawableRes imageResId: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    // All photos are saved in drawable, so the app works offline.
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(SoftWhite)
            .border(1.dp, Mist, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun HeritageIllustration(kind: IllustrationKind, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(SoftWhite, RoundedCornerShape(8.dp))
            .border(1.dp, Mist, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            drawRoundRect(
                color = Color(0xFFEAF4F6),
                cornerRadius = CornerRadius(18f, 18f),
                size = size
            )
            drawRect(
                color = Sand,
                topLeft = Offset(0f, h * 0.72f),
                size = Size(w, h * 0.28f)
            )

            when (kind) {
                IllustrationKind.Mausoleum -> {
                    drawCircle(Gold, radius = w * 0.10f, center = Offset(w * 0.5f, h * 0.34f))
                    drawRect(MediterraneanBlue, Offset(w * 0.28f, h * 0.40f), Size(w * 0.44f, h * 0.28f))
                    drawRect(Clay, Offset(w * 0.22f, h * 0.35f), Size(w * 0.08f, h * 0.38f))
                    drawRect(Clay, Offset(w * 0.70f, h * 0.35f), Size(w * 0.08f, h * 0.38f))
                    drawRect(SoftWhite, Offset(w * 0.43f, h * 0.50f), Size(w * 0.14f, h * 0.18f))
                }
                IllustrationKind.CultureCity -> {
                    drawRect(DeepBlue, Offset(w * 0.25f, h * 0.30f), Size(w * 0.50f, h * 0.34f))
                    drawRect(Clay, Offset(w * 0.32f, h * 0.22f), Size(w * 0.36f, h * 0.12f))
                    repeat(4) { index ->
                        drawRect(Gold, Offset(w * (0.32f + index * 0.09f), h * 0.40f), Size(w * 0.045f, h * 0.12f))
                    }
                }
                IllustrationKind.Stadium -> {
                    drawOval(Olive, Offset(w * 0.18f, h * 0.30f), Size(w * 0.64f, h * 0.38f))
                    drawOval(SoftWhite, Offset(w * 0.28f, h * 0.38f), Size(w * 0.44f, h * 0.22f))
                    drawRect(MediterraneanBlue, Offset(w * 0.36f, h * 0.44f), Size(w * 0.28f, h * 0.10f))
                }
                IllustrationKind.BrutalistHotel -> {
                    val path = Path().apply {
                        moveTo(w * 0.20f, h * 0.28f)
                        lineTo(w * 0.80f, h * 0.28f)
                        lineTo(w * 0.64f, h * 0.68f)
                        lineTo(w * 0.36f, h * 0.68f)
                        close()
                    }
                    drawPath(path, DeepBlue)
                    repeat(5) { index ->
                        drawLine(
                            color = SoftWhite,
                            start = Offset(w * 0.28f, h * (0.34f + index * 0.06f)),
                            end = Offset(w * 0.72f, h * (0.34f + index * 0.06f)),
                            strokeWidth = 3f
                        )
                    }
                }
                IllustrationKind.Theatre -> {
                    drawRoundRect(Clay, Offset(w * 0.22f, h * 0.30f), Size(w * 0.56f, h * 0.38f), CornerRadius(14f, 14f))
                    drawArc(
                        color = Gold,
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        topLeft = Offset(w * 0.32f, h * 0.20f),
                        size = Size(w * 0.36f, h * 0.24f),
                        style = Stroke(8f)
                    )
                    drawRect(SoftWhite, Offset(w * 0.42f, h * 0.46f), Size(w * 0.16f, h * 0.22f))
                }
                IllustrationKind.Library -> {
                    drawRect(MediterraneanBlue, Offset(w * 0.30f, h * 0.24f), Size(w * 0.40f, h * 0.48f))
                    repeat(5) { index ->
                        drawRect(SoftWhite, Offset(w * (0.35f + index * 0.06f), h * 0.32f), Size(w * 0.03f, h * 0.28f))
                    }
                    drawRect(Gold, Offset(w * 0.24f, h * 0.70f), Size(w * 0.52f, h * 0.04f))
                }
                IllustrationKind.Wetland -> {
                    drawOval(MediterraneanBlue, Offset(w * 0.16f, h * 0.44f), Size(w * 0.68f, h * 0.20f))
                    drawCircle(Olive, w * 0.15f, Offset(w * 0.58f, h * 0.34f))
                    drawLine(DeepBlue, Offset(w * 0.30f, h * 0.36f), Offset(w * 0.40f, h * 0.28f), Stroke(4f).width)
                    drawLine(DeepBlue, Offset(w * 0.40f, h * 0.28f), Offset(w * 0.50f, h * 0.36f), Stroke(4f).width)
                }
                IllustrationKind.Mountain -> {
                    val mountain = Path().apply {
                        moveTo(w * 0.15f, h * 0.72f)
                        lineTo(w * 0.48f, h * 0.24f)
                        lineTo(w * 0.85f, h * 0.72f)
                        close()
                    }
                    drawPath(mountain, DeepBlue)
                    drawPath(Path().apply {
                        moveTo(w * 0.40f, h * 0.36f)
                        lineTo(w * 0.48f, h * 0.24f)
                        lineTo(w * 0.58f, h * 0.37f)
                        close()
                    }, SoftWhite)
                }
                IllustrationKind.Savanna -> {
                    drawCircle(Gold, w * 0.10f, Offset(w * 0.78f, h * 0.24f))
                    repeat(4) { index ->
                        val x = w * (0.25f + index * 0.12f)
                        drawLine(Olive, Offset(x, h * 0.72f), Offset(x + w * 0.04f, h * 0.48f), 6f)
                        drawCircle(Olive, w * 0.045f, Offset(x + w * 0.05f, h * 0.46f))
                    }
                }
                IllustrationKind.Island -> {
                    drawOval(MediterraneanBlue, Offset(w * 0.08f, h * 0.44f), Size(w * 0.84f, h * 0.28f))
                    drawPath(Path().apply {
                        moveTo(w * 0.32f, h * 0.60f)
                        lineTo(w * 0.50f, h * 0.26f)
                        lineTo(w * 0.70f, h * 0.60f)
                        close()
                    }, Olive)
                }
                IllustrationKind.Gulf -> {
                    drawArc(MediterraneanBlue, 0f, 180f, true, Offset(w * 0.14f, h * 0.38f), Size(w * 0.72f, h * 0.34f))
                    drawPath(Path().apply {
                        moveTo(w * 0.18f, h * 0.72f)
                        lineTo(w * 0.38f, h * 0.36f)
                        lineTo(w * 0.58f, h * 0.72f)
                        close()
                    }, Olive)
                }
                IllustrationKind.Oasis -> {
                    drawCircle(Gold, w * 0.10f, Offset(w * 0.76f, h * 0.25f))
                    drawOval(MediterraneanBlue, Offset(w * 0.28f, h * 0.58f), Size(w * 0.44f, h * 0.12f))
                    drawLine(Clay, Offset(w * 0.46f, h * 0.64f), Offset(w * 0.50f, h * 0.36f), 8f)
                    repeat(5) { index ->
                        val endX = w * (0.34f + index * 0.08f)
                        drawLine(Olive, Offset(w * 0.50f, h * 0.38f), Offset(endX, h * 0.30f), 6f)
                    }
                }
            }
        }
    }
}

@Composable
fun SettingRow(label: String, enabled: Boolean, onToggle: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        OutlinedButton(onClick = onToggle, shape = RoundedCornerShape(8.dp)) {
            Text(if (enabled) "On" else "Off")
        }
    }
}

@Composable
fun SpacerSmall() {
    Spacer(modifier = Modifier.size(4.dp))
}
