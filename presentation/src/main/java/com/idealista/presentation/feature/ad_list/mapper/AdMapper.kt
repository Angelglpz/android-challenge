package com.idealista.presentation.feature.ad_list.mapper

import com.idealista.domain.model.ad.Ad
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs

fun Ad.toAdDetailArgs() = AdDetailArgs(
    adId = propertyCode,
    address = address,
    district = district,
    municipality = municipality
)