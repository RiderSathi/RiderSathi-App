package com.example.ridersathi.ui.screens.report

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun ReportIssueScreen(navController: NavController) {
    val issueTypes = listOf(
        IssueType("Pothole", Icons.Default.Warning, WarmOrange),
        IssueType("Accident", Icons.Default.CarCrash, ErrorRed),
        IssueType("Fuel Shortage", Icons.Default.LocalGasStation, Color(0xFFFFD600)),
        IssueType("Roadblock", Icons.Default.Block, Color(0xFFFF6B6B)),
        IssueType("Unsafe Zone", Icons.Default.Warning, Color(0xFFFFAB00)),
        IssueType("Other", Icons.Default.Help, TextGray)
    )

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
                    text = "Report an Issue",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = TextWhite
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "What's the issue?",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Issue Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(issueTypes) { issue ->
                    IssueCard(issue = issue, onClick = { /* Navigate to details */ })
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Report Button
            Button(
                onClick = { /* Submit */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonCyan,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Report Issue", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Composable
fun IssueCard(issue: IssueType, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(CharcoalMedium, RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(issue.color.copy(alpha = 0.2f), Color.Transparent)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = issue.icon,
                    contentDescription = null,
                    tint = issue.color,
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = issue.name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = TextWhite
            )
        }
    }
}

data class IssueType(val name: String, val icon: ImageVector, val color: Color)
