package com.idealista.presentation.feature.ad_list.event

import com.idealista.domain.model.ad.Ad

internal sealed class AdListEvent {
    class OnFavoritesClicked(val ad: Ad) : AdListEvent()
}