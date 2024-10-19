package com.idealista.presentation.feature.ad_list.mapper

import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.presentation.feature.ad_list.util.formatPrice
import com.idealista.presentation.feature.ad_list.vo.AdImagesVO
import com.idealista.presentation.feature.ad_list.vo.AdVO
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs

fun AdVO.toAdDetailArgs() = AdDetailArgs(
    id = id,
    address = address,
    district = district,
    municipality = municipality
)

fun Ad.toVO(adFavorite: AdFavorite?): AdVO {
    val imagesList = multimedia.images.map { AdImagesVO(it.url, it.tag) }
    return AdVO(
        id = id,
        images = imagesList,
        address = address,
        propertyType = propertyType,
        district = district,
        municipality = municipality,
        price = priceInfo.amount.formatPrice(currencySuffix = priceInfo.currencySuffix),
        rooms = rooms,
        size = size,
        exterior = exterior,
        floor = floor,
        isFavorite = adFavorite?.isFavorite ?: false
    )
}