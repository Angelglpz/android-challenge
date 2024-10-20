package com.idealista.data.mapper.ad

import com.idealista.domain.model.ad.PropertyType
import java.util.Locale

fun String.toPropertyTypeEnum(): PropertyType {
    return try {
        PropertyType.valueOf(this.uppercase(Locale.getDefault()))
    } catch (e: IllegalArgumentException) {
        PropertyType.UNKNOWN
    }
}