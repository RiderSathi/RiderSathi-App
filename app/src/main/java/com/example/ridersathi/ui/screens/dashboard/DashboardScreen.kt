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
            .background(CharcoalDark)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            item {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = TextWhite
                    )
                    IconButton(onClick = { navController.navigate("profile_tab") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = TextWhite)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Welcome Message
                Text(
                    text = "Welcome back, Alex",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = TextWhite
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search destination...", color = TextGray) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = NeonCyan) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NeonCyan,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.1f),
                        focusedContainerColor = CharcoalMedium,
                        unfocusedContainerColor = CharcoalMedium,
                        cursorColor = NeonCyan,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite
                    ),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Quick Access Header
                Text(
                    text = "Quick Access",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = TextWhite
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Quick Access Grid (2x2)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    QuickAccessCard(
                        title = "Navigate",
                        icon = Icons.Default.Navigation,
                        color = NeonCyan,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("map_tab") }
                    )
                    QuickAccessCard(
                        title = "Nearby Petrol Pumps",
                        icon = Icons.Default.LocalGasStation,
                        color = Color(0xFFFFD600),
                        modifier = Modifier.weight(1f),
                        onClick = { }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    QuickAccessCard(
                        title = "Add Alert",
                        icon = Icons.Default.Warning,
                        color = WarmOrange,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("report_issue") }
                    )
                    QuickAccessCard(
                        title = "Safe Routes",
                        icon = Icons.Default.Shield,
                        color = Color(0xFF00E676),
                        modifier = Modifier.weight(1f),
                        onClick = { }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Community Updates Header
                Text(
                    text = "Community Updates",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = TextWhite
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Community Updates List
            items(
                listOf(
                    CommunityUpdate("Road Closure on Main Street", "Due to construction, Main Street will be closed between 10th and 18th Avenue. Please use alternate routes.", "2 hours ago", Icons.Default.Construction, Color(0xFFFFD600)),
                    CommunityUpdate("Accident Reported on Highway 101", "An accident has been reported on Highway 101 Exit 45. Expect delays and consider using alternate routes.", "Yesterday", Icons.Default.CarCrash, ErrorRed)
                )
            ) { update ->
                CommunityUpdateCard(update = update)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun QuickAccessCard(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(CharcoalMedium, RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(color.copy(alpha = 0.3f), Color.Transparent)
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = TextWhite
            )
        }
    }
}

@Composable
fun CommunityUpdateCard(update: CommunityUpdate) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CharcoalMedium, RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(update.color.copy(alpha = 0.2f), CircleShape),
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
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = update.description,
                style = MaterialTheme.typography.bodySmall,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = update.time,
                style = MaterialTheme.typography.labelSmall,
                color = TextGray.copy(alpha = 0.6f)
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
