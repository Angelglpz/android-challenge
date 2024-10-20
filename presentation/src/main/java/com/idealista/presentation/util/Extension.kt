package com.idealista.presentation.util

import android.view.View
import com.idealista.domain.model.ad.PropertyType
import com.idealista.presentation.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun PropertyType.getResourceString(): Int {
    return when (this) {
        PropertyType.FLAT -> R.string.property_type_flat
        PropertyType.HOMES -> R.string.property_type_homes
        PropertyType.UNKNOWN -> R.string.property_type_empty
    }
}

internal fun View.visible() {
    visibility = View.VISIBLE
}

internal fun View.gone() {
    visibility = View.GONE
}

internal fun Double.formatNoFraction(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
    formatter.maximumFractionDigits = 0
    return formatter.format(this)
}

internal fun Long.toDateFormatted(patternToFormat: String = Constants.DEFAULT_DATE_FORMAT): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(patternToFormat, Locale.getDefault())
    return formatter.format(date)
}
