package com.idealista.data.mapper.ad_favorite

import com.idealista.data.entity.AdFavoriteEntity
import com.idealista.domain.model.ad_detail.AdFavorite

internal fun AdFavoriteEntity.toDomain() = AdFavorite(
    id = id,
    isFavorite = isFavorite,
    date = date
)

internal fun AdFavorite.toEntity() = AdFavoriteEntity(
    id = id,
    isFavorite = isFavorite,
    date = date
)