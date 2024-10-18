package com.idealista.presentation.feature.ad_list.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.idealista.presentation.R

@Composable
fun customLightColorScheme() = lightColorScheme(
    primary = colorResource(id = R.color.magenta_light),
    onPrimary = colorResource(id = R.color.green_dark),
    secondary = colorResource(id = R.color.green_light),
    onSecondary = colorResource(id = R.color.magenta_dark),
    background = colorResource(id = R.color.white)
)

@Composable
fun customDarkColorScheme() = darkColorScheme(
    primary = colorResource(id = R.color.magenta_dark),
    onPrimary = colorResource(id = R.color.green_light),
    secondary = colorResource(id = R.color.green_dark),
    onSecondary = colorResource(id = R.color.magenta_light),
    background = colorResource(id = R.color.black)
)

@Composable
fun AdListTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) customDarkColorScheme() else customLightColorScheme(),
        content = content
    )
}