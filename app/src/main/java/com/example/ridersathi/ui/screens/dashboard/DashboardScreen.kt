package com.example.ridersathi.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ridersathi.ui.theme.*

@Composable
fun DashboardScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            item {
                // Header with Avatar and Search
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Avatar
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .shadow(4.dp, CircleShape)
                                .clip(CircleShape)
                                .background(Brush.linearGradient(listOf(AccentPurple, AccentPink))),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "A",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Hello, Alex",
                                style = MaterialTheme.typography.titleMedium,
                                color = TextDark,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Today ${java.time.LocalDate.now().dayOfMonth} ${java.time.LocalDate.now().month.toString().take(3)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextGray
                            )
                        }
                    }
                    IconButton(onClick = { /* Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = TextDark)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Daily Challenge Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(8.dp, RoundedCornerShape(24.dp))
                        .clip(RoundedCornerShape(24.dp))
                        .background(Brush.linearGradient(listOf(GradientPurpleStart, GradientPurpleEnd)))
                        .padding(20.dp)
                ) {
                    Column {
                        Text(
                            text = "Daily\nchallenge",
                            style = MaterialTheme.typography.headlineSmall,
                            color = TextDark,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Do your plan before 09:00 AM",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // Avatar Group
                        Row {
                            repeat(3) { index ->
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .offset(x = (-8 * index).dp)
                                        .shadow(2.dp, CircleShape)
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .border(2.dp, Color.White, CircleShape)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Your Plan Header
                Text(
                    text = "Your plan",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextDark,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Quick Access Grid (2x2)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ModernQuickAccessCard(
                        title = "Navigate",
                        subtitle = "Find routes",
                        backgroundColor = SoftYellow,
                        icon = Icons.Default.Navigation,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("map_tab") }
                    )
                    ModernQuickAccessCard(
                        title = "Petrol",
                        subtitle = "Nearby pumps",
                        backgroundColor = SoftBlue,
                        icon = Icons.Default.LocalGasStation,
                        modifier = Modifier.weight(1f),
                        onClick = { }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ModernQuickAccessCard(
                        title = "Add Alert",
                        subtitle = "Report issue",
                        backgroundColor = SoftPink,
                        icon = Icons.Default.Warning,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("report_issue") }
                    )
                    ModernQuickAccessCard(
                        title = "Safe Routes",
                        subtitle = "Best paths",
                        backgroundColor = SoftGreen,
                        icon = Icons.Default.Shield,
                        modifier = Modifier.weight(1f),
                        onClick = { }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Community Updates Header
                Text(
                    text = "Community Updates",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextDark,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Community Updates List
            items(
                listOf(
                    CommunityUpdate("Road Closure on Main Street", "Due to construction, Main Street will be closed. Please use alternate routes.", "2h ago", Icons.Default.Construction, WarningOrange),
                    CommunityUpdate("Accident on Highway 101", "An accident has been reported. Expect delays.", "Yesterday", Icons.Default.CarCrash, ErrorRed)
                )
            ) { update ->
                ModernCommunityUpdateCard(update = update)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ModernQuickAccessCard(
    title: String,
    subtitle: String,
    backgroundColor: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .shadow(4.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextDark,
                modifier = Modifier.size(32.dp)
            )
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextDark,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMedium
                )
            }
        }
    }
}

@Composable
fun ModernCommunityUpdateCard(update: CommunityUpdate) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(CardWhite)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(update.color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = update.icon,
                contentDescription = null,
                tint = update.color,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = update.title,
                style = MaterialTheme.typography.titleSmall,
                color = TextDark,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = update.description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = update.time,
                style = MaterialTheme.typography.labelSmall,
                color = TextLight
            )
        }
    }
}

data class CommunityUpdate(
    val title: String,
    val description: String,
    val time: String,
    val icon: ImageVector,
    val color: Color
)
