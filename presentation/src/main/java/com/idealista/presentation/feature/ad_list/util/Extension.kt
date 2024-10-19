package com.idealista.presentation.feature.ad_list.util

import java.text.NumberFormat
import java.util.Locale

internal fun Double.formatPrice(
    formatWithCurrency: Boolean = true,
    currencySuffix: String = "€"
): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
    formatter.maximumFractionDigits = 0
    return if (formatWithCurrency) {
        "${formatter.format(this)} $currencySuffix".replace(",", ".")
    } else {
        formatter.format(this).replace(",", ".")
    }
}