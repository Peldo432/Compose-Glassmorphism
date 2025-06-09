package org.fitness.project.components

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PulsingAvatar(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    emoji: String = "🤖"
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAnim"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.25f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    val floatYRaw by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatY"
    )

    val floatY = with(LocalDensity.current) { floatYRaw.toDp() }

    val density = LocalDensity.current
    val radiusPx = with(density) { (size * 1.6f).toPx() }

    Box(
        modifier = modifier
            .offset(y = floatY)
            .size(size)
            .graphicsLayer {
                scaleX = pulse
                scaleY = pulse
                shadowElevation = 12f
                shape = CircleShape
                clip = true
                ambientShadowColor = Color.Black.copy(alpha = 0.70f)
                spotShadowColor = Color.Black.copy(alpha = 0.70f)
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            for (i in 1..3) {
                drawCircle(
                    color = Color.Cyan.copy(alpha = glowAlpha / i),
                    radius = radiusPx * (i / 3f),
                    center = center
                )
            }
        }

        Text(
            text = emoji,
            fontSize = 32.sp
        )
    }
}