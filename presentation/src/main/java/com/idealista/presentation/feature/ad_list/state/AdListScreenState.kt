package com.idealista.presentation.feature.ad_list.state

import com.idealista.domain.model.ad.Ad


data class AdListScreenState(
    val adList: List<Ad> = emptyList(),
    val showLoading: Boolean = false
)
