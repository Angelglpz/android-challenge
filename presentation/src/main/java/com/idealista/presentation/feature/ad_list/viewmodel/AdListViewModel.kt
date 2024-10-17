package com.idealista.presentation.feature.ad_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idealista.domain.usecase.GetAdsListUseCase
import com.idealista.domain.util.ApiResponseStatus
import com.idealista.presentation.feature.ad_list.event.AdListEvent
import com.idealista.presentation.feature.ad_list.state.AdListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdListViewModel @Inject constructor(
    private val getAdsListUseCase: GetAdsListUseCase
) : ViewModel() {

    var state by mutableStateOf(AdListScreenState())

    init {
        getAdsList()
    }

    internal fun onEvent(event: AdListEvent) {
        when (event) {
            is AdListEvent.OnFavoritesClicked -> {
                state = state.copy(
                    adList = state.adList.map { ad ->
                        if (ad.propertyCode == event.ad.propertyCode) {
                            ad.copy(isFavorite = !ad.isFavorite)
                        } else {
                            ad
                        }
                    }
                )
            }
        }
    }

    private fun getAdsList() {
        viewModelScope.launch {
            state = state.copy(showLoading = true)
            when (val result = getAdsListUseCase()) {
                is ApiResponseStatus.Success -> {
                    state = state.copy(adList = result.data, showLoading = false)
                }

                is ApiResponseStatus.Error -> {
                    // Handle error
                }
            }
        }
    }
}