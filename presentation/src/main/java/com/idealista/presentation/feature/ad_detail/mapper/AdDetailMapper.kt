package com.idealista.presentation.feature.ad_detail.mapper

import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.model.ad_detail.ImageDetail
import com.idealista.presentation.feature.ad_detail.vo.AdDetailImagesVO
import com.idealista.presentation.feature.ad_detail.vo.AdDetailVO
import com.idealista.presentation.feature.ad_list.util.formatPrice
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs

object AdDetailMapper {
    fun mapToVO(ad: AdDetail, adDetailArgs: AdDetailArgs?, adFavorite: AdFavorite): AdDetailVO {
        return AdDetailVO(
            images = ad.multimedia.images.map { it.toVO() },
            address = adDetailArgs?.address ?: "",
            addressLocation = "${adDetailArgs?.district}, ${adDetailArgs?.municipality}",
            propertyType = ad.propertyType,
            price = ad.priceInfo.amount.formatPrice(),
            currency = ad.priceInfo.currencySuffix,
            rooms = ad.moreCharacteristics.roomNumber,
            size = ad.moreCharacteristics.constructedArea,
            exterior = ad.moreCharacteristics.exterior,
            floor = ad.moreCharacteristics.floor,
            isFavorite = adFavorite.isFavorite
        )

    }

    private fun ImageDetail.toVO() = AdDetailImagesVO(
        url = url,
        tag = tag
    )
}