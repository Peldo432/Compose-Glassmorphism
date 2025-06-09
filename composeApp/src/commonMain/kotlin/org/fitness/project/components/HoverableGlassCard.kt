package org.fitness.project.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HoverableGlassCard(
    modifier: Modifier = Modifier,
    defaultElevation: Dp = 12.dp,
    hoveredElevation: Dp = 24.dp,
    defaultScale: Float = 1f,
    hoveredScale: Float = 1.03f, // New: Scale on hover
    pressedScale: Float = 0.95f,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentElevation by animateDpAsState(
        targetValue = if (isHovered) hoveredElevation else defaultElevation,
        animationSpec = tween(durationMillis = 200),
        label = "cardElevationAnimation"
    )

    val currentScale by animateFloatAsState(
        targetValue = when {
            isHovered -> hoveredScale
            isPressed -> pressedScale
            else -> defaultScale
        },
        animationSpec = tween(durationMillis = 200),
        label = "cardScaleAnimation"
    )

    Box(
        modifier = modifier
            .hoverable(interactionSource = interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberGlassyRippleIndication(),
                enabled = onClick != null,
                onClick = { onClick?.invoke() }
            )
            .graphicsLayer {
                shadowElevation = currentElevation.toPx()
                scaleX = currentScale
                scaleY = currentScale

                ambientShadowColor =
                    Color.Black.copy(alpha = if (isHovered) 0.5f else 0.3f)
                spotShadowColor =
                    Color.Black.copy(alpha = if (isHovered) 0.6f else 0.4f)

                shape = RoundedCornerShape(20.dp)
                clip = true
            }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF33334A).copy(alpha = 0.6f),
                        Color(0xFF202030).copy(alpha = 0.3f)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.08f),
                        Color.White.copy(alpha = 0.02f)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        content()
    }
}