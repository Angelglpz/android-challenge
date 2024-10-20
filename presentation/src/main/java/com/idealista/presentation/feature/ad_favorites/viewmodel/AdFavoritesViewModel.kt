package com.idealista.presentation.feature.ad_favorites.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.usecase.ad_favorite.DeleteAdFavoriteUseCase
import com.idealista.domain.usecase.ad_favorite.GetAllFavoriteAdsUseCase
import com.idealista.domain.usecase.ad_list.GetAdsListUseCase
import com.idealista.presentation.feature.ad_favorites.mapper.AdFavoritesMapper
import com.idealista.presentation.feature.ad_favorites.vo.AdFavoriteListVO
import com.idealista.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdFavoritesViewModel @Inject constructor(
    private val getAdsListUseCase: GetAdsListUseCase,
    private val getAllFavoriteAdsUseCase: GetAllFavoriteAdsUseCase,
    private val deleteAdFavoriteUseCase: DeleteAdFavoriteUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _adFavorites: MutableLiveData<List<AdFavoriteListVO>> = MutableLiveData()
    val adFavorites: MutableLiveData<List<AdFavoriteListVO>> = _adFavorites

    private val _showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showLoading: SingleLiveEvent<Boolean> = _showLoading

    private val _showError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showError: SingleLiveEvent<Boolean> = _showError

    fun onResume() {
        viewModelScope.launch {
            retrieveData()
        }
    }

    private suspend fun retrieveData() = coroutineScope {
        _showLoading.postValue(true)
        val adsDeferred = async { getAdsListUseCase() }
        val allFavoritesDeferred = async { getAllFavoriteAdsUseCase() }

        val adsResult = adsDeferred.await()
        val adFavoritesResult = allFavoritesDeferred.await().first()

        var adList: List<Ad>? = null
        adsResult.fold(
            onSuccess = {
                adList = it
            },
            onFailure = {
                _showError.postValue(true)
                _showLoading.postValue(false)
            }
        )

        if (adsResult.isSuccess) {
            val idsFavorites = adFavoritesResult.map { it.id }.toSet()
            val adsFavorites = adList?.filter { idsFavorites.contains(it.id) } ?: emptyList()
            _adFavorites.postValue(adsFavorites.map { ad ->
                AdFavoritesMapper.mapToVo(
                    ad,
                    adFavoritesResult.find { it.id == ad.id },
                    context.resources
                )
            })
            _showLoading.postValue(false)
        }

    }

    fun onFavoriteButtonClicked(id: Int) {
        viewModelScope.launch {
            deleteAdFavoriteUseCase(id)
        }
    }
}