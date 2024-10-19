package com.idealista.presentation.feature.ad_list.util

import java.text.NumberFormat
import java.util.Locale

internal fun Double.formatPrice(currencySuffix: String = ""): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
    formatter.maximumFractionDigits = 0
    return "${formatter.format(this)} $currencySuffix".replace(",", ".")
}