package com.idealista.presentation.feature.ad_favorites.mapper

import android.content.res.Resources
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.presentation.R
import com.idealista.presentation.feature.ad_favorites.vo.AdFavoriteListVO
import com.idealista.presentation.feature.ad_list.util.formatPrice
import com.idealista.presentation.util.formatNoFraction
import com.idealista.presentation.util.toDateFormatted

object AdFavoritesMapper {
    fun mapToVo(ad: Ad, adFavorite: AdFavorite?, resources: Resources): AdFavoriteListVO {
        val outerInnerText =
            if (ad.exterior) resources.getString(R.string.outer) else resources.getString(R.string.inner)
        val floor = if (ad.floor == "0") resources.getString(R.string.ground_floor) else ad.floor
        return AdFavoriteListVO(
            id = adFavorite?.id ?: 0,
            thumbnail = ad.thumbnail,
            address = ad.address,
            propertyType = ad.propertyType,
            district = ad.district,
            municipality = ad.municipality,
            price = ad.priceInfo.amount.formatPrice(currencySuffix = ad.priceInfo.currencySuffix),
            rooms = resources.getString(R.string.rooms, ad.rooms),
            size = resources.getString(R.string.size, ad.size.formatNoFraction()),
            floor = resources.getString(R.string.floor_info, floor, outerInnerText),
            favorite = resources.getString(R.string.remove_favorite),
            dateFavorite = resources.getString(
                R.string.favorite_date, adFavorite?.date?.toDateFormatted() ?: ""
            )
        )
    }
}
