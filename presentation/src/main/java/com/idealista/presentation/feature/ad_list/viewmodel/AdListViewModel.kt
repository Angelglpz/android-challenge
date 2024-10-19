package com.idealista.presentation.feature.ad_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.usecase.ad_favorite.DeleteAdFavoriteUseCase
import com.idealista.domain.usecase.ad_favorite.GetAllFavoriteAdsUseCase
import com.idealista.domain.usecase.ad_favorite.SaveAdFavoriteUseCase
import com.idealista.domain.usecase.ad_list.GetAdsListUseCase
import com.idealista.presentation.feature.ad_list.event.AdListEvent
import com.idealista.presentation.feature.ad_list.mapper.toAdDetailArgs
import com.idealista.presentation.feature.ad_list.mapper.toVO
import com.idealista.presentation.feature.ad_list.state.AdListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdListViewModel @Inject constructor(
    private val getAdsListUseCase: GetAdsListUseCase,
    private val saveAdFavoriteUseCase: SaveAdFavoriteUseCase,
    private val getAllFavoriteAdsUseCase: GetAllFavoriteAdsUseCase,
    private val deleteAdFavoriteUseCase: DeleteAdFavoriteUseCase
) : ViewModel() {

    var state by mutableStateOf(AdListScreenState())
        private set

    init {
        viewModelScope.launch {
            retrieveData()
        }
    }

    internal fun onEvent(event: AdListEvent) {
        state = when (event) {
            is AdListEvent.OnFavoritesClicked -> {
                if (event.ad.isFavorite) {
                    deleteFavoriteOnDatabase(event.ad.id)
                } else {
                    saveFavoriteOnDatabase(getAdFavorite(event))
                }
                state.copy(
                    adList = state.adList.map { ad ->
                        if (ad.id == event.ad.id) {
                            ad.copy(isFavorite = !ad.isFavorite)
                        } else {
                            ad
                        }
                    }
                )
            }

            is AdListEvent.OnAdClicked -> state.copy(navigateToDetailWithArgs = event.ad.toAdDetailArgs())
        }
    }

    private fun getAdFavorite(event: AdListEvent.OnFavoritesClicked) =
        AdFavorite(
            event.ad.id,
            !event.ad.isFavorite,
            System.currentTimeMillis()
        )

    private suspend fun retrieveData() = coroutineScope {
        state = state.copy(showLoading = true)

        val adListDeferred = async { getAdsListUseCase() }
        getAllFavoriteAdsUseCase().collect { adsFavoritesResult ->
            val adsListResult = adListDeferred.await()

            var adsList: List<Ad>? = null

            adsListResult.fold(
                onSuccess = {
                    adsList = it
                },
                onFailure = {
                    state = state.copy(showLoading = false)
                }
            )

            val adListFavorite: List<AdFavorite> = adsFavoritesResult
            updateStateWithAdsData(adsList, adListFavorite)
        }
    }

    private fun updateStateWithAdsData(
        adsList: List<Ad>?,
        adFavorites: List<AdFavorite>?
    ) {
        if (adsList != null && adFavorites != null) {
            val adVOList = adsList.map { ad ->
                val favorite = adFavorites.find { it.id == ad.id }
                ad.toVO(favorite)
            }

            state = state.copy(adList = adVOList, showLoading = false)
        } else {
            state = state.copy(showLoading = false)
        }
    }


    private fun saveFavoriteOnDatabase(ad: AdFavorite) {
        viewModelScope.launch {
            saveAdFavoriteUseCase(ad)
        }
    }

    private fun deleteFavoriteOnDatabase(id: Int) {
        viewModelScope.launch {
            deleteAdFavoriteUseCase(id)
        }
    }

    fun cleanNavigateToDetail() {
        state = state.copy(navigateToDetailWithArgs = null)
    }
}