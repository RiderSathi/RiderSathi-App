package com.example.ridersathi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ridersathi.ui.screens.login.LoginScreen
import com.example.ridersathi.ui.screens.main.MainScreen
import com.example.ridersathi.ui.screens.onboarding.OnboardingScreen
import com.example.ridersathi.ui.screens.permissions.PermissionsScreen
import com.example.ridersathi.ui.screens.signup.SignupScreen
import com.example.ridersathi.ui.screens.splash.SplashScreen
import com.example.ridersathi.ui.theme.RiderSathiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RiderSathiTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("splash") {
                            SplashScreen(navController = navController)
                        }
                        composable("onboarding") {
                            OnboardingScreen(navController = navController)
                        }
                        composable("permissions") {
                            PermissionsScreen(navController = navController)
                        }
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("signup") {
                            SignupScreen(navController = navController)
                        }
                        composable("main") {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}