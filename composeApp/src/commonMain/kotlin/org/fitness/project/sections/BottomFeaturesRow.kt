package org.fitness.project.sections

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.fitness.project.components.FeatureCard

@Composable
fun BottomFeaturesRow() {
    val floatAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )
    val items = listOf(
        Triple("📊", "Smart Analytics", "AI-powered insights"),
        Triple("🎯", "Adaptive Goals", "Personalized targets"),
        Triple("🍩", "Next Cheat Day", "Sunday – Treat incoming!"),
        Triple("📿", "Next Ekadashi", "4th June – Devotional Reset")
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { (emoji, title, subtitle) ->
                    FeatureCard(
                        emoji = emoji,
                        title = title,
                        subtitle = subtitle,
                        modifier = Modifier
                            .weight(1f)
                            .offset(y = floatAnimation.dp)
                            .height(180.dp)
                    )
                }

                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}