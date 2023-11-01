package com.phatnv.widgets.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    error: Color,
    isLight: Boolean
) {
    private var primary by mutableStateOf(primary)
    private var textSecondary by mutableStateOf(textSecondary)
    private var textPrimary by mutableStateOf(textPrimary)
    private var error by mutableStateOf(error)
    private var background by mutableStateOf(background)
    private var isLight by mutableStateOf(isLight)

    fun copy(
        primary: Color = this.primary,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        error: Color = this.error,
        background: Color = this.background,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        textPrimary,
        textSecondary,
        error,
        background,
        isLight
    )

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        background = other.background
        error = other.error
    }
}

var colorLightPrimary = Color(0xFFFFB400)
    private set
var colorLightTextPrimary = Color(0xFF000000)
    private set
var colorLightTextSecondary = Color(0xFF6C727A)
    private set
var colorLightBackground = Color(0xFFFFFFFF)
    private set
var colorLightError = Color(0xFFD62222)
    private set

var colorDarkPrimary = Color(0xFF0037FF)
    private set
var colorDarkTextPrimary = Color(0xFFFAFAFA)
    private set
var colorDarkTextSecondary = Color(0xFF6C727A)
    private set
var colorDarkBackground = Color(0xFF090A0A)
    private set
var colorDarkError = Color(0xFFD62222)
    private set

fun lightColors(
    primary: Color = colorLightPrimary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    error: Color = colorLightError
): AppColors = AppColors(
    primary = primary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = true
)

fun darkColors(
    primary: Color = colorDarkPrimary,
    textPrimary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    error: Color = colorDarkError
): AppColors = AppColors(
    primary = primary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = false
)

val LocalColors = staticCompositionLocalOf { lightColors() }