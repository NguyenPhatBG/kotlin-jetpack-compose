package com.phatnv.widgets.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.phatnv.widgets.R

private val light = Font(R.font.raleway_light, FontWeight.W300)
private val regular = Font(R.font.raleway_regular, FontWeight.W400)
private val medium = Font(R.font.raleway_medium, FontWeight.W500)
private val semibold = Font(R.font.raleway_semibold, FontWeight.W600)

private val rapewayFontFamily = FontFamily(fonts = listOf(light, regular, medium, semibold))

val reuseTextStyle = TextStyle(
    fontFamily = rapewayFontFamily,
    fontWeight = FontWeight.W400,
)

data class AppTypography(
    val h1: TextStyle = TextStyle(fontSize = 96.sp).merge(reuseTextStyle),
    val h2: TextStyle = TextStyle(fontSize = 60.sp).merge(reuseTextStyle),
    val h3: TextStyle = TextStyle(fontSize = 48.sp).merge(reuseTextStyle),
    val h4: TextStyle = TextStyle(fontSize = 34.sp).merge(reuseTextStyle),
    val h5: TextStyle = TextStyle(fontSize = 24.sp).merge(reuseTextStyle),
    val h6: TextStyle = TextStyle(fontSize = 20.sp).merge(reuseTextStyle),
    val titleLarge: TextStyle = TextStyle(fontSize = 18.sp).merge(reuseTextStyle),
    val titleMedium: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        fontFamily = rapewayFontFamily,
    ),
    val titleSmall: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        fontFamily = rapewayFontFamily,
    ),
    val bodyLarge: TextStyle = TextStyle(fontSize = 16.sp).merge(reuseTextStyle),
    val bodyMedium: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        fontFamily = rapewayFontFamily,
    ),
    val bodySmall: TextStyle = TextStyle(fontSize = 12.sp).merge(reuseTextStyle),
    val labelLarge: TextStyle = TextStyle(fontSize = 14.sp).merge(reuseTextStyle),
    val labelMedium: TextStyle = TextStyle(fontSize = 12.sp).merge(reuseTextStyle),
    val labelSmall: TextStyle = TextStyle(fontSize = 10.sp).merge(reuseTextStyle),
)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }