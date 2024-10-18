package com.idealista.presentation.feature.ad_list.state

import com.idealista.presentation.feature.ad_list.vo.AdVO
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs


data class AdListScreenState(
    val adList: List<AdVO> = emptyList(),
    val navigateToDetailWithArgs: AdDetailArgs? = null,
    val showLoading: Boolean = false
)
