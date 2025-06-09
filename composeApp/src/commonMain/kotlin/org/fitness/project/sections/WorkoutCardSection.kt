package org.fitness.project.sections

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.fitness.project.components.HoverableGlassCard
import org.fitness.project.components.StatItem
import org.fitness.project.components.rememberGlassyRippleIndication
import org.fitness.project.components.ShimmerProgressBar

@Composable
fun WorkoutCardSection() {
    val floatAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    HoverableGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = floatAnimation.dp)
            .padding(top = 24.dp),
        defaultElevation = 12.dp,
        hoveredElevation = 24.dp,
        defaultScale = 1f,
        hoveredScale = 1.02f,
        onClick = { println("🔥 Workout Card Clicked!") }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "🔥 Today's AI Workout",
                color = Color(0xFFE0E0E0),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatItem("45", "MIN")
                StatItem("320", "KCAL")
                StatItem("12", "EXERCISES")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Weekly Progress",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                )
                Text(
                    text = "73%",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }

            ShimmerProgressBar(
                progress = 0.73f,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {

            }

            val buttonInteractionSource = remember { MutableInteractionSource() }
            val isButtonPressed by buttonInteractionSource.collectIsPressedAsState()
            val buttonScale by animateFloatAsState(
                targetValue = if (isButtonPressed) 0.95f else 1f,
                animationSpec = tween(200),
                label = "buttonScale"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .offset(
                        y = rememberInfiniteTransition(label = "floatButton").animateFloat(
                            initialValue = 0f,
                            targetValue = -6f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(durationMillis = 1400, easing = LinearEasing),
                                repeatMode = RepeatMode.Reverse
                            ),
                            label = "offsetAnim"
                        ).value.dp
                    )
                    .graphicsLayer {
                        scaleX = buttonScale
                        scaleY = buttonScale
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFFE56C63),
                                Color(0xFFC86A6B)
                            )
                        )
                    )
                    .clickable(
                        interactionSource = buttonInteractionSource,
                        indication = rememberGlassyRippleIndication()
                    ) {
                        println("🔥 Button Clicked!")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Start Workout with AI Coach",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}