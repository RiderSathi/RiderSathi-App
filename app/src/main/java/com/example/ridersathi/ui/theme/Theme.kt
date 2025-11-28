package com.example.ridersathi.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    secondary = WarmOrange,
    tertiary = NeonCyanDim,
    background = CharcoalDark,
    surface = CharcoalMedium,
    onPrimary = CharcoalDark,
    onSecondary = CharcoalDark,
    onTertiary = CharcoalDark,
    onBackground = TextWhite,
    onSurface = TextWhite,
    error = ErrorRed
)

// We force dark theme for the "Cinematic" look, but keeping LightColorScheme for completeness/fallback
private val LightColorScheme = lightColorScheme(
    primary = NeonCyan,
    secondary = WarmOrange,
    tertiary = NeonCyanDim,
    background = CharcoalDark, // Force dark background even in light mode for brand consistency
    surface = CharcoalMedium,
    onPrimary = CharcoalDark,
    onSecondary = CharcoalDark,
    onTertiary = CharcoalDark,
    onBackground = TextWhite,
    onSurface = TextWhite,
    error = ErrorRed
)

@Composable
fun RiderSathiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // We default to FALSE to maintain the "Cinematic Warm Darks" aesthetic
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme // Use DarkColorScheme even for light theme if we want to enforce the look
    }
    
    // Force the cinematic look regardless of system setting if not using dynamic colors
    val finalColorScheme = if (!dynamicColor) DarkColorScheme else colorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = finalColorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false // Always dark status bar
        }
    }

    MaterialTheme(
        colorScheme = finalColorScheme,
        typography = Typography,
        content = content
    )
}