package org.fitness.project

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.fitness.project.components.ChallengeChip
import org.fitness.project.sections.BottomFeaturesRow
import org.fitness.project.sections.TopGreetingSection
import org.fitness.project.sections.WorkoutCardSection
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val infiniteTransition = rememberInfiniteTransition()

    val color1 by infiniteTransition.animateColor(
        initialValue = Color(0xFF1A1A2E),
        targetValue = Color(0xFF16213E),
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val color2 by infiniteTransition.animateColor(
        initialValue = Color(0xFF0F3460),
        targetValue = Color(0xFF533483),
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val floatAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animatedBackground = Brush.verticalGradient(
        colors = listOf(color1, color2)
    )

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = animatedBackground
                )
        ) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState, flingBehavior = ScrollableDefaults.flingBehavior())
                    .padding(all = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    ChallengeChip(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(y = floatAnimation.dp)
                            .padding(top = 12.dp, end = 16.dp)
                    )

                    TopGreetingSection(
                        username = "Jethalal",
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = floatAnimation.dp)
                            .padding(top = 48.dp)
                    )
                }
                WorkoutCardSection()
                BottomFeaturesRow()
            }
        }
    }
}










