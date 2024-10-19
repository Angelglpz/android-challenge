package com.idealista.presentation.feature.ad_favorites.vo

import com.idealista.domain.model.ad.PropertyType

data class AdFavoriteListVO(
    val id: Int,
    val thumbnail: String,
    val address: String,
    val propertyType: PropertyType,
    val district: String,
    val municipality: String,
    val price: String,
    val rooms: String,
    val size: String,
    val floor: String,
    val favorite: String,
    val dateFavorite: String
)