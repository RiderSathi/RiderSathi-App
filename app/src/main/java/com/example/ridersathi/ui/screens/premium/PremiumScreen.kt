package com.example.ridersathi.ui.screens.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ridersathi.ui.theme.*

@Composable
fun PremiumScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CharcoalDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextWhite)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Premium Features",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = TextWhite
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Unlock the full potential\nof your ride",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Monthly Plan
            PlanCard(
                title = "Monthly",
                price = "$9.99",
                period = "/month",
                features = listOf(
                    "Ad-Free Experience",
                    "Smart Route Planning",
                    "Advanced Fuel & Service Tracking"
                ),
                isRecommended = false,
                buttonText = "Select Plan"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Yearly Plan
            PlanCard(
                title = "Yearly",
                price = "$99.99",
                period = "/year",
                features = listOf(
                    "Ad-Free Experience",
                    "Smart Route Planning",
                    "Advanced Fuel & Service Tracking"
                ),
                isRecommended = true,
                buttonText = "Subscribe Now"
            )

            Spacer(modifier = Modifier.weight(1f))

            // Upgrade Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(NeonCyan, Color(0xFF00E676))
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Upgrade to Premium",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = CharcoalDark
                    )
                    Text(
                        "Unlock exclusive features and benefits",
                        style = MaterialTheme.typography.bodySmall,
                        color = CharcoalDark.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun PlanCard(
    title: String,
    price: String,
    period: String,
    features: List<String>,
    isRecommended: Boolean,
    buttonText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CharcoalMedium, RoundedCornerShape(16.dp))
            .border(
                width = if (isRecommended) 2.dp else 1.dp,
                color = if (isRecommended) NeonCyan else Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(20.dp)
    ) {
        if (isRecommended) {
            Box(
                modifier = Modifier
                    .background(NeonCyan.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    "Best Value",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = NeonCyan
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextWhite
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = price,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = if (isRecommended) NeonCyan else TextWhite
            )
            Text(
                text = period,
                style = MaterialTheme.typography.bodyMedium,
                color = TextGray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        features.forEach { feature ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = if (isRecommended) NeonCyan else Color(0xFF00E676),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = feature,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextWhite
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Subscribe */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isRecommended) NeonCyan else CharcoalDark,
                contentColor = if (isRecommended) Color.Black else TextWhite
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(buttonText, fontWeight = FontWeight.Bold)
        }
    }
}
