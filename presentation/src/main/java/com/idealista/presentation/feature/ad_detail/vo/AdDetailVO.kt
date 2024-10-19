package com.idealista.presentation.feature.ad_detail.vo

import com.idealista.domain.model.ad.PropertyType
import com.idealista.domain.model.ad_detail.MoreCharacteristicsDetail

data class AdDetailVO(
    val id: Int,
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
    val commentary: String,
    val basicCharacteristics: MoreCharacteristicsDetail
)

data class AdDetailImagesVO(
    val url: String,
    val tag: String
)

data class AdDetailFavoriteVO(
    val id: Int,
    val isFavorite: Boolean,
    val date: String
)
