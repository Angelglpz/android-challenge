package com.idealista.presentation.util

import android.content.Context
import com.idealista.domain.model.ad.PropertyType
import com.idealista.presentation.R

fun Int.pxToDp(context: Context): Float {
    val density = context.resources.displayMetrics.density
    return this / density
}

fun PropertyType.getResourceString(): Int {
    return when (this) {
        PropertyType.FLAT -> R.string.property_type_flat
        PropertyType.HOMES -> R.string.property_type_homes
    }
}
