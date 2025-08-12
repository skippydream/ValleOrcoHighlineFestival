package com.tsf.valleorcohighlinefestival.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// Shapes (opzionale ma utile)
val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp)
)

// Light theme
private val LightColors = lightColorScheme(
    primary = Color(0xFF7C5F89),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE6D7ED),
    onPrimaryContainer = Color(0xFF2E1A38),

    secondary = Color(0xFFAC6D59),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFE3D3),
    onSecondaryContainer = Color(0xFF4C2B21),

    background = Color(0xFFFAF6F4),
    onBackground = Color(0xFF2D1B32),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2D1B32),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF)
)

// Dark theme
private val DarkColors = darkColorScheme(
    primary = Color(0xFFD2BBE6),
    onPrimary = Color(0xFF3A2B47),
    primaryContainer = Color(0xFF4F3C5E),
    onPrimaryContainer = Color(0xFFEBDDF6),

    secondary = Color(0xFFFFB59C),
    onSecondary = Color(0xFF4C2B21),
    secondaryContainer = Color(0xFF5E3B2F),
    onSecondaryContainer = Color(0xFFFFDBCF),

    background = Color(0xFF1E1A22),
    onBackground = Color(0xFFEDE1F1),

    surface = Color(0xFF2B2630),
    onSurface = Color(0xFFEDE1F1),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

@Composable
fun ValleOrcoHighlineFestivalTheme(
    useDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (isSystemInDarkTheme()) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        isSystemInDarkTheme() -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes,
        typography = Typography(), // usa Typography() se non hai una tipografia custom
        content = content
    )
}
