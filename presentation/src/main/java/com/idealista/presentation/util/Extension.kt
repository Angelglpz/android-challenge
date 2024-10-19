package com.idealista.presentation.util

import android.content.Context
import android.view.View
import com.idealista.domain.model.ad.PropertyType
import com.idealista.presentation.R
import java.text.NumberFormat
import java.util.Locale

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

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

internal fun Double.formatNoFraction(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
    formatter.maximumFractionDigits = 0
    return formatter.format(this)
}
