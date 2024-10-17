package com.idealista.core.util

import android.content.Context

fun Int.pxToDp(context: Context): Float {
    val density = context.resources.displayMetrics.density
    return this / density
}
