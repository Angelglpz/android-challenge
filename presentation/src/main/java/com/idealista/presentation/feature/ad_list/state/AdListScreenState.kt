package com.idealista.presentation.feature.ad_list.state

import com.idealista.domain.model.ad.Ad
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs


data class AdListScreenState(
    val adList: List<Ad> = emptyList(),
    val navigateToDetailWithArgs: AdDetailArgs? = null,
    val showLoading: Boolean = false
)
