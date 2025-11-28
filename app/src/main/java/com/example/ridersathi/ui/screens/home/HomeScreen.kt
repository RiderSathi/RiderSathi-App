package com.example.ridersathi.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ridersathi.ui.theme.CharcoalDark
import com.example.ridersathi.ui.theme.CharcoalMedium
import com.example.ridersathi.ui.theme.ErrorRed
import com.example.ridersathi.ui.theme.NeonCyan
import com.example.ridersathi.ui.theme.TextGray
import com.example.ridersathi.ui.theme.TextWhite
import com.example.ridersathi.ui.theme.WarmOrange
// import com.mapbox.maps.extension.compose.MapboxMap // Commented out until dependency is fully resolved/synced

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var showZoneDetails by remember { mutableStateOf(false) }

    if (showZoneDetails) {
        ModalBottomSheet(
            onDismissRequest = { showZoneDetails = false },
            containerColor = CharcoalMedium,
            contentColor = TextWhite
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = ErrorRed,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "High Accident Zone",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = TextWhite
                        )
                        Text(
                            text = "Severity: High",
                            style = MaterialTheme.typography.labelMedium,
                            color = ErrorRed
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "This area has a high frequency of accidents reported in the last 30 days. Please drive with caution and maintain a safe distance.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextGray
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { showZoneDetails = false },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NeonCyan,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Got it", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    // Add Marker State
    var addMarkerState by remember { mutableStateOf(AddMarkerStep.NONE) }
    var selectedMarkerType by remember { mutableStateOf<MarkerType?>(null) }

    if (addMarkerState != AddMarkerStep.NONE) {
        ModalBottomSheet(
            onDismissRequest = { addMarkerState = AddMarkerStep.NONE },
            containerColor = CharcoalMedium,
            contentColor = TextWhite
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                if (addMarkerState == AddMarkerStep.SELECT_TYPE) {
                    Text(
                        text = "Report Hazard",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val markerTypes = listOf(
                        MarkerType("Accident", Icons.Default.Warning, ErrorRed),
                        MarkerType("Pothole", Icons.Default.Warning, WarmOrange), // Use appropriate icon
                        MarkerType("Police", Icons.Default.Security, NeonCyan),
                        MarkerType("Traffic", Icons.Default.DirectionsBike, TextWhite)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(markerTypes) { type ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable {
                                        selectedMarkerType = type
                                        addMarkerState = AddMarkerStep.ENTER_DETAILS
                                    }
                                    .padding(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .background(CharcoalDark, CircleShape)
                                        .border(1.dp, type.color, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(type.icon, contentDescription = null, tint = type.color)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(type.name, style = MaterialTheme.typography.bodyMedium, color = TextWhite)
                            }
                        }
                    }
                } else if (addMarkerState == AddMarkerStep.ENTER_DETAILS) {
                    Text(
                        text = "Details: ${selectedMarkerType?.name}",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text("Severity", color = TextGray)
                    // Slider placeholder (using Row of buttons for simplicity)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Low", "Medium", "High").forEach { level ->
                            SuggestionChip(
                                onClick = { /* Select Level */ },
                                label = { Text(level, color = TextWhite) },
                                colors = SuggestionChipDefaults.suggestionChipColors(containerColor = CharcoalDark)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = { addMarkerState = AddMarkerStep.NONE }, // Submit
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = NeonCyan, contentColor = Color.Black),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Submit Report", fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Map Placeholder (Replace with MapboxMap when ready)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CharcoalDark)
                .clickable { showZoneDetails = true } // Simulate zone click
        ) {
            // Simulated Map Background
            Text(
                text = "Mapbox Map Area\n(Tap to simulate Zone Click)",
                color = TextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Top Overlay: Search Bar & Chips
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Where to?", color = TextGray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextGray) },
                trailingIcon = {
                    IconButton(onClick = { navController.navigate("dashboard") }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = NeonCyan)
                    }
                },
                colors = SearchBarDefaults.colors(
                    containerColor = CharcoalMedium,
                    dividerColor = TextGray,
                    inputFieldColors = SearchBarDefaults.inputFieldColors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite
                    )
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Search suggestions would go here
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOf("Home", "Work", "Saved rides")) { label ->
                    SuggestionChip(
                        onClick = { /* Navigate */ },
                        label = { Text(label, color = TextWhite) },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = CharcoalMedium,
                            labelColor = TextWhite
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
        }

        // Floating Action Buttons (Left Side)
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FloatingActionButton(
                onClick = { /* Open Camera */ },
                containerColor = WarmOrange,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Camera")
            }

            FloatingActionButton(
                onClick = { /* SOS */ },
                containerColor = ErrorRed,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Text("SOS", style = MaterialTheme.typography.labelLarge)
            }

            FloatingActionButton(
                onClick = { /* Headset/Intercom */ },
                containerColor = NeonCyan,
                contentColor = CharcoalDark,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Headset, contentDescription = "Intercom")
            }
            
            // Add Marker FAB
            FloatingActionButton(
                onClick = { addMarkerState = AddMarkerStep.SELECT_TYPE },
                containerColor = TextWhite,
                contentColor = CharcoalDark,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Marker")
            }
        }

        // Bottom Sheet / Quick Actions (Simulated)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(CharcoalMedium, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(24.dp)
        ) {
            Text(
                text = "Nearby Services",
                style = MaterialTheme.typography.titleMedium,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QuickActionItem(icon = Icons.Default.LocalGasStation, label = "Petrol", color = Color(0xFF448AFF))
                QuickActionItem(icon = Icons.Default.Warning, label = "Tyre shop", color = Color(0xFFFFD600))
            }
        }
    }
}

enum class AddMarkerStep {
    NONE, SELECT_TYPE, ENTER_DETAILS
}

data class MarkerType(val name: String, val icon: ImageVector, val color: Color)

@Composable
fun QuickActionItem(icon: ImageVector, label: String, color: Color) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(80.dp)
            .background(CharcoalDark, RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = TextWhite
        )
    }
}
