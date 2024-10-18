package com.idealista.data.mapper.ad_favorite

import com.idealista.data.entity.AdFavoriteEntity
import com.idealista.domain.model.ad_detail.AdFavorite

fun AdFavoriteEntity.toDomain() = AdFavorite(
    id = id,
    isFavorite = isFavorite,
    date = date
)

fun AdFavorite.toEntity() = AdFavoriteEntity(
    id = id,
    isFavorite = isFavorite,
    date = date
)