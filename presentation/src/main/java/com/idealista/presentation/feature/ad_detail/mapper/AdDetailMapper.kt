package com.idealista.presentation.feature.ad_detail.mapper

import android.content.res.Resources
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.model.ad_detail.ImageDetail
import com.idealista.domain.model.ad_detail.MoreCharacteristicsDetail
import com.idealista.presentation.R
import com.idealista.presentation.feature.ad_detail.vo.AdDetailFavoriteVO
import com.idealista.presentation.feature.ad_detail.vo.AdDetailImagesVO
import com.idealista.presentation.feature.ad_detail.vo.AdDetailVO
import com.idealista.presentation.feature.ad_list.util.formatPrice
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs
import com.idealista.presentation.util.formatNoFraction
import com.idealista.presentation.util.toDateFormatted

object AdDetailMapper {
    fun mapToVO(ad: AdDetail, adDetailArgs: AdDetailArgs?): AdDetailVO {
        return AdDetailVO(
            id = adDetailArgs?.id ?: 0,
            images = ad.multimedia.images.map { it.toVO() },
            address = adDetailArgs?.address ?: "",
            addressLocation = "${adDetailArgs?.district}, ${adDetailArgs?.municipality}",
            propertyType = ad.propertyType,
            price = ad.priceInfo.amount.formatPrice(formatWithCurrency = false),
            currency = ad.priceInfo.currencySuffix,
            rooms = ad.moreCharacteristics.roomNumber,
            size = ad.moreCharacteristics.constructedArea,
            exterior = ad.moreCharacteristics.exterior,
            floor = ad.moreCharacteristics.floor,
            commentary = ad.propertyComment,
            basicCharacteristics = ad.moreCharacteristics
        )
    }

    private fun ImageDetail.toVO() = AdDetailImagesVO(
        url = url,
        tag = tag
    )
}

fun AdFavorite?.toVO() = AdDetailFavoriteVO(
    id = this?.id ?: 0,
    isFavorite = this?.isFavorite ?: false,
    date = this?.date?.toDateFormatted() ?: ""
)

fun MoreCharacteristicsDetail.mapCharacteristicsToText(resources: Resources): List<String> {
    return listOf(
        resources.getString(R.string.community_costs, communityCosts.formatPrice()),
        resources.getString(R.string.bathrooms, bathNumber),
        resources.getString(
            R.string.size_constructed,
            constructedArea.toDouble().formatNoFraction()
        ),
        if (lift) resources.getString(R.string.lift) else resources.getString(R.string.no_lift),
        if (boxroom) resources.getString(R.string.boxroom) else resources.getString(R.string.no_boxroom)
    ).toMutableList()
}