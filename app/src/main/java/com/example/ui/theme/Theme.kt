package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = DarkMaroon,
    onPrimary = RoyalMaroonDark,
    primaryContainer = DarkMaroonContainer,
    onPrimaryContainer = DarkMaroon,
    secondary = DarkGold,
    onSecondary = SandalwoodBrown,
    secondaryContainer = SandalwoodBrown,
    onSecondaryContainer = SilkGoldLight,
    tertiary = EmeraldAccent,
    background = DarkBackground,
    onBackground = WarmIvoryBackground,
    surface = DarkSurface,
    onSurface = WarmIvoryBackground
  )

private val LightColorScheme =
  lightColorScheme(
    primary = RoyalMaroon,
    onPrimary = Color.White,
    primaryContainer = PalaceGoldContainer,
    onPrimaryContainer = RoyalMaroonDark,
    secondary = SilkGold,
    onSecondary = Color.White,
    secondaryContainer = SandalwoodLight,
    onSecondaryContainer = SandalwoodBrown,
    tertiary = EmeraldAccent,
    background = WarmIvoryBackground,
    onBackground = TextDark,
    surface = WarmSurface,
    onSurface = TextDark
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
