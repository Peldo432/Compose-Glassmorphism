package org.fitness.project.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    barHeight: Dp = 12.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerAnim"
    )

    Box(
        modifier = modifier
            .height(barHeight)
            .clip(RoundedCornerShape(50))
            .background(Color.White.copy(alpha = 0.1f))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val barWidth = size.width * progress
            val shimmerWidth = size.width / 3

            drawRoundRect(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFFFF8A00), Color(0xFFFFD600))
                ),
                topLeft = Offset.Zero,
                size = Size(barWidth, size.height),
                cornerRadius = CornerRadius(size.height / 2),
                alpha = 1f
            )

            val shimmerStart = barWidth * shimmerOffset - shimmerWidth
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.1f),
                        Color.White.copy(alpha = 0.5f),
                        Color.White.copy(alpha = 0.1f)
                    ),
                    start = Offset(shimmerStart, 0f),
                    end = Offset(shimmerStart + shimmerWidth, 0f)
                ),
                topLeft = Offset.Zero,
                size = Size(barWidth, size.height),
                cornerRadius = CornerRadius(size.height / 2)
            )
        }
    }
}