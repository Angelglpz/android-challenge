package com.idealista.presentation.feature.ad_detail.vo

import com.idealista.domain.model.ad.PropertyType

data class AdDetailVO(
    val images: List<AdDetailImagesVO>,
    val address: String,
    val addressLocation: String,
    val propertyType: PropertyType,
    val price: String,
    val currency: String,
    val rooms: Int,
    val size: Int,
    val exterior: Boolean,
    val floor: String,
    val isFavorite: Boolean
)

data class AdDetailImagesVO(
    val url: String,
    val tag: String
)
