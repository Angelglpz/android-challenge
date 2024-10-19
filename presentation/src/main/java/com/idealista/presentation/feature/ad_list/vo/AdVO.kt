package com.idealista.presentation.feature.ad_list.vo

import com.idealista.domain.model.ad.PropertyType


data class AdVO(
    val id: Int,
    val images: List<AdImagesVO>,
    val propertyType: PropertyType,
    val address: String,
    val district: String,
    val municipality: String,
    val price: String,
    val rooms: Int,
    val size: Double,
    val exterior: Boolean,
    val floor: String,
    val isFavorite: Boolean
)

data class AdImagesVO(
    val url: String,
    val tag: String
)
