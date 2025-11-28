package com.example.ridersathi.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ridersathi.ui.screens.community.CommunityScreen
import com.example.ridersathi.ui.screens.dashboard.DashboardScreen
import com.example.ridersathi.ui.screens.home.HomeScreen
import com.example.ridersathi.ui.screens.premium.PremiumScreen
import com.example.ridersathi.ui.screens.profile.ProfileScreen
import com.example.ridersathi.ui.screens.report.ReportIssueScreen
import com.example.ridersathi.ui.screens.sos.SosScreen
import com.example.ridersathi.ui.theme.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // List of routes that should show the bottom bar
    val bottomBarRoutes = listOf("home_tab", "map_tab", "community_tab", "profile_tab")
    val showBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = CardWhite,
                    contentColor = TextDark,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .shadow(8.dp, RoundedCornerShape(24.dp))
                ) {
                    val currentDestination = navBackStackEntry?.destination

                    val items = listOf(
                        NavigationItem("Home", "home_tab", Icons.Default.Home),
                        NavigationItem("Map", "map_tab", Icons.Default.Map),
                        NavigationItem("Community", "community_tab", Icons.Default.People),
                        NavigationItem("Profile", "profile_tab", Icons.Default.Person)
                    )

                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = null,
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = AccentPurple,
                                selectedTextColor = AccentPurple,
                                indicatorColor = SoftPurple,
                                unselectedIconColor = TextGray,
                                unselectedTextColor = TextGray
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home_tab",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home_tab") {
                DashboardScreen(navController = navController)
            }
            composable("map_tab") {
                HomeScreen(navController = navController)
            }
            composable("community_tab") {
                CommunityScreen(navController = navController)
            }
            composable("profile_tab") {
                ProfileScreen(navController = navController)
            }
            composable("report_issue") {
                ReportIssueScreen(navController = navController)
            }
            composable("premium") {
                PremiumScreen(navController = navController)
            }
            composable("sos") {
                SosScreen(navController = navController)
            }
        }
    }
}

data class NavigationItem(val label: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
