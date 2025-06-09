package org.fitness.project.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.invalidateDraw
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GlassyRippleIndication(
    private val pressedColor: Color = Color.White.copy(alpha = 0.07f),
    private val rippleColor: Color = Color.White.copy(alpha = 0.13f)
) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return GlassyRippleNode(interactionSource.interactions, pressedColor, rippleColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GlassyRippleIndication) return false
        if (pressedColor != other.pressedColor) return false
        if (rippleColor != other.rippleColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = pressedColor.hashCode()
        result = 31 * result + rippleColor.hashCode()
        return result
    }
}

class GlassyRippleNode(
    private val interactionSource: Flow<Interaction>,
    private val pressedColor: Color,
    private val rippleColor: Color
) : Modifier.Node(), DrawModifierNode {

    private var currentPress: PressInteraction.Press? by mutableStateOf(null)

    private val animatedAlpha = Animatable(0f)
    private val animatedScale = Animatable(0.8f)
    private val animatedRotation = Animatable(0f)

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        currentPress = interaction
                        coroutineScope.launch {
                            animatedAlpha.snapTo(0.3f)
                            animatedAlpha.animateTo(0f, tween(600))
                        }
                        coroutineScope.launch {
                            animatedScale.snapTo(0.6f)
                            animatedScale.animateTo(1.1f, spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            ))
                        }
                        coroutineScope.launch {
                            animatedRotation.snapTo(0f)
                            animatedRotation.animateTo(180f, tween(600))
                        }
                    }
                    is PressInteraction.Release, is PressInteraction.Cancel -> {
                        currentPress = null
                    }
                }
                invalidateDraw()
            }
        }
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        val press = currentPress ?: return
        val center = press.pressPosition

        drawCircle(
            color = pressedColor,
            radius = size.minDimension * 0.09f * animatedScale.value,
            center = center,
            alpha = animatedAlpha.value
        )

        repeat(8) { i ->
            val angle = i * 45f + animatedRotation.value
            withTransform({
                rotate(angle, center)
            }) {
                drawOval(
                    color = rippleColor,
                    topLeft = Offset(center.x - 12, center.y - 60 * animatedScale.value),
                    size = Size(24f, 90f * animatedScale.value),
                    alpha = animatedAlpha.value * 0.5f
                )
            }
        }
    }
}


@Composable
fun rememberGlassyRippleIndication(
    pressedColor: Color = Color.White.copy(alpha = 0.07f),
    rippleColor: Color = Color.White.copy(alpha = 0.13f)
): Indication {
    return remember(pressedColor, rippleColor) {
        GlassyRippleIndication(pressedColor, rippleColor)
    }
}
