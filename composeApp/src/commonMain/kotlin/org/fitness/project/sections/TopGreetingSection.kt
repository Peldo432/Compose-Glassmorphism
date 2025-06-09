package org.fitness.project.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.fitness.project.components.PulsingAvatar


@Composable
fun TopGreetingSection(
    modifier: Modifier = Modifier,
    username: String = "Alex"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PulsingAvatar(modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Good Morning, $username!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE0E0E0)
            )
            Text(
                text = "Ready to crush your fitness goals?",
                fontSize = 14.sp,
                color = Color(0xFFAAAAAA)
            )
        }
    }
}