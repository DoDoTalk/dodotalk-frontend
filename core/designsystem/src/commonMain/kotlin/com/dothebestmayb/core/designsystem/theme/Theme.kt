package com.dothebestmayb.core.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalExtendedColors = staticCompositionLocalOf {
    LightExtendedColors
}

val ColorScheme.extended: ExtendedColors
    @ReadOnlyComposable
    @Composable
    get() = LocalExtendedColors.current

@Immutable
data class ExtendedColors(
    // Button states
    val primaryHover: Color,
    val destructiveHover: Color,
    val destructiveSecondaryOutline: Color,
    val disabledOutline: Color,
    val disabledFill: Color,
    val successOutline: Color,
    val success: Color,
    val onSuccess: Color,
    val secondaryFill: Color,

    // Text variants
    val textPrimary: Color,
    val textTertiary: Color,
    val textSecondary: Color,
    val textPlaceholder: Color,
    val textDisabled: Color,

    // Surface variants
    val surfaceLower: Color,
    val surfaceHigher: Color,
    val surfaceOutline: Color,
    val overlay: Color,

    // Accent colors
    val accentBlue: Color,
    val accentPurple: Color,
    val accentViolet: Color,
    val accentPink: Color,
    val accentOrange: Color,
    val accentYellow: Color,
    val accentGreen: Color,
    val accentTeal: Color,
    val accentLightBlue: Color,
    val accentGrey: Color,

    // Cake colors for chat bubbles
    val cakeViolet: Color,
    val cakeGreen: Color,
    val cakeBlue: Color,
    val cakePink: Color,
    val cakeOrange: Color,
    val cakeYellow: Color,
    val cakeTeal: Color,
    val cakePurple: Color,
    val cakeRed: Color,
    val cakeMint: Color,
)

val LightExtendedColors = ExtendedColors(
    primaryHover = DoDoTalkBrand600,
    destructiveHover = DoDoTalkRed600,
    destructiveSecondaryOutline = DoDoTalkRed200,
    disabledOutline = DoDoTalkBase200,
    disabledFill = DoDoTalkBase150,
    successOutline = DoDoTalkBrand100,
    success = DoDoTalkBrand600,
    onSuccess = DoDoTalkBase0,
    secondaryFill = DoDoTalkBase100,

    textPrimary = DoDoTalkBase1000,
    textTertiary = DoDoTalkBase800,
    textSecondary = DoDoTalkBase900,
    textPlaceholder = DoDoTalkBase700,
    textDisabled = DoDoTalkBase400,

    surfaceLower = DoDoTalkBase100,
    surfaceHigher = DoDoTalkBase100,
    surfaceOutline = DoDoTalkBase1000Alpha14,
    overlay = DoDoTalkBase1000Alpha80,

    accentBlue = DoDoTalkBlue,
    accentPurple = DoDoTalkPurple,
    accentViolet = DoDoTalkViolet,
    accentPink = DoDoTalkPink,
    accentOrange = DoDoTalkOrange,
    accentYellow = DoDoTalkYellow,
    accentGreen = DoDoTalkGreen,
    accentTeal = DoDoTalkTeal,
    accentLightBlue = DoDoTalkLightBlue,
    accentGrey = DoDoTalkGrey,

    cakeViolet = DoDoTalkCakeLightViolet,
    cakeGreen = DoDoTalkCakeLightGreen,
    cakeBlue = DoDoTalkCakeLightBlue,
    cakePink = DoDoTalkCakeLightPink,
    cakeOrange = DoDoTalkCakeLightOrange,
    cakeYellow = DoDoTalkCakeLightYellow,
    cakeTeal = DoDoTalkCakeLightTeal,
    cakePurple = DoDoTalkCakeLightPurple,
    cakeRed = DoDoTalkCakeLightRed,
    cakeMint = DoDoTalkCakeLightMint,
)

val DarkExtendedColors = ExtendedColors(
    primaryHover = DoDoTalkBrand600,
    destructiveHover = DoDoTalkRed600,
    destructiveSecondaryOutline = DoDoTalkRed200,
    disabledOutline = DoDoTalkBase900,
    disabledFill = DoDoTalkBase1000,
    successOutline = DoDoTalkBrand500Alpha40,
    success = DoDoTalkBrand500,
    onSuccess = DoDoTalkBase1000,
    secondaryFill = DoDoTalkBase900,

    textPrimary = DoDoTalkBase0,
    textTertiary = DoDoTalkBase200,
    textSecondary = DoDoTalkBase150,
    textPlaceholder = DoDoTalkBase400,
    textDisabled = DoDoTalkBase500,

    surfaceLower = DoDoTalkBase1000,
    surfaceHigher = DoDoTalkBase900,
    surfaceOutline = DoDoTalkBase100Alpha10Alt,
    overlay = DoDoTalkBase1000Alpha80,

    accentBlue = DoDoTalkBlue,
    accentPurple = DoDoTalkPurple,
    accentViolet = DoDoTalkViolet,
    accentPink = DoDoTalkPink,
    accentOrange = DoDoTalkOrange,
    accentYellow = DoDoTalkYellow,
    accentGreen = DoDoTalkGreen,
    accentTeal = DoDoTalkTeal,
    accentLightBlue = DoDoTalkLightBlue,
    accentGrey = DoDoTalkGrey,

    cakeViolet = DoDoTalkCakeDarkViolet,
    cakeGreen = DoDoTalkCakeDarkGreen,
    cakeBlue = DoDoTalkCakeDarkBlue,
    cakePink = DoDoTalkCakeDarkPink,
    cakeOrange = DoDoTalkCakeDarkOrange,
    cakeYellow = DoDoTalkCakeDarkYellow,
    cakeTeal = DoDoTalkCakeDarkTeal,
    cakePurple = DoDoTalkCakeDarkPurple,
    cakeRed = DoDoTalkCakeDarkRed,
    cakeMint = DoDoTalkCakeDarkMint,
)

val LightColorScheme = lightColorScheme(
    primary = DoDoTalkBrand500,
    onPrimary = DoDoTalkBrand1000, // Primary Color 위에 그려지는 색상
    primaryContainer = DoDoTalkBrand1000,
    onPrimaryContainer = DoDoTalkBrand900,

    secondary = DoDoTalkBase700,
    onSecondary = DoDoTalkBase0,
    secondaryContainer = DoDoTalkBase100,
    onSecondaryContainer = DoDoTalkBase900,

    tertiary = DoDoTalkBrand900,
    onTertiary = DoDoTalkBase0,
    tertiaryContainer = DoDoTalkBrand100,
    onTertiaryContainer = DoDoTalkBrand1000,

    error = DoDoTalkRed500,
    onError = DoDoTalkBase0,
    errorContainer = DoDoTalkRed200,
    onErrorContainer = DoDoTalkRed600,

    background = DoDoTalkBrand1000,
    onBackground = DoDoTalkBase0,
    surface = DoDoTalkBase0,
    onSurface = DoDoTalkBase1000,
    surfaceVariant = DoDoTalkBase100,
    onSurfaceVariant = DoDoTalkBase900,

    outline = DoDoTalkBase1000Alpha8,
    outlineVariant = DoDoTalkBase200,
)

val DarkColorScheme = darkColorScheme(
    primary = DoDoTalkBrand500,
    onPrimary = DoDoTalkBrand1000,
    primaryContainer = DoDoTalkBrand900,
    onPrimaryContainer = DoDoTalkBrand500,

    secondary = DoDoTalkBase400,
    onSecondary = DoDoTalkBase1000,
    secondaryContainer = DoDoTalkBase900,
    onSecondaryContainer = DoDoTalkBase150,

    tertiary = DoDoTalkBrand500,
    onTertiary = DoDoTalkBase1000,
    tertiaryContainer = DoDoTalkBrand900,
    onTertiaryContainer = DoDoTalkBrand500,

    error = DoDoTalkRed500,
    onError = DoDoTalkBase0,
    errorContainer = DoDoTalkRed600,
    onErrorContainer = DoDoTalkRed200,

    background = DoDoTalkBase1000,
    onBackground = DoDoTalkBase0,
    surface = DoDoTalkBase950,
    onSurface = DoDoTalkBase0,
    surfaceVariant = DoDoTalkBase900,
    onSurfaceVariant = DoDoTalkBase150,

    outline = DoDoTalkBase100Alpha10,
    outlineVariant = DoDoTalkBase800,
)
