package com.idealista.presentation.feature.ad_list.event

import com.idealista.presentation.feature.ad_list.vo.AdVO

internal sealed class AdListEvent {
    class OnFavoritesClicked(val ad: AdVO) : AdListEvent()
    class OnAdClicked(val ad: AdVO) : AdListEvent()
}