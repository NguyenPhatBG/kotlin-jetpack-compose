package com.phatnv.widgets.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object AppTheme {
    val colors: AppColors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography: AppTypography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val dimensions: AppDimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current

    val shapes: AppShapes
        @Composable @ReadOnlyComposable get() = LocalShapes.current
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: AppColors = AppTheme.colors,
    typography: AppTypography = AppTheme.typography,
    dimensions: AppDimensions = AppTheme.dimensions,
    shapes: AppShapes = AppTheme.shapes,
    content: @Composable () -> Unit
) {
    // Explicitly creating a new object here so we don't mutate the initial [colors]
    // provided, and overwrite the values set in it.
    val rememberedColors = remember { colors.copy() }.apply {
        if (darkTheme) {
            updateColorsFrom(darkColors())
        } else {
            updateColorsFrom(lightColors())
        }
    }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocalShapes provides shapes
    ) {
        content()
    }
}