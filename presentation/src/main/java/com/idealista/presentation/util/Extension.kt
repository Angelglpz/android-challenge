package com.idealista.presentation.util

import android.content.Context

fun Int.pxToDp(context: Context): Float {
    val density = context.resources.displayMetrics.density
    return this / density
}
