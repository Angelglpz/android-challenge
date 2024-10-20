package com.idealista.presentation.feature.ad_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.usecase.ad_detail.GetAdDetailUseCase
import com.idealista.domain.usecase.ad_favorite.DeleteAdFavoriteUseCase
import com.idealista.domain.usecase.ad_favorite.GetAdDetailFavoriteUseCase
import com.idealista.domain.usecase.ad_favorite.SaveAdFavoriteUseCase
import com.idealista.presentation.feature.ad_detail.mapper.AdDetailMapper
import com.idealista.presentation.feature.ad_detail.mapper.toVO
import com.idealista.presentation.feature.ad_detail.util.Constants
import com.idealista.presentation.feature.ad_detail.vo.AdDetailFavoriteVO
import com.idealista.presentation.feature.ad_detail.vo.AdDetailVO
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs
import com.idealista.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAdDetailUseCase: GetAdDetailUseCase,
    private val getAdDetailFavoriteUseCase: GetAdDetailFavoriteUseCase,
    private val saveAdFavoriteUseCase: SaveAdFavoriteUseCase,
    private val deleteAdFavoriteUseCase: DeleteAdFavoriteUseCase
) : ViewModel() {

    private val _adDetail = MutableLiveData<AdDetailVO>()
    val adDetail: LiveData<AdDetailVO> get() = _adDetail

    private val _favorite = MutableLiveData<AdDetailFavoriteVO>()
    val favorite: LiveData<AdDetailFavoriteVO> get() = _favorite

    private val _showLoading = SingleLiveEvent<Boolean>()
    val showLoading: SingleLiveEvent<Boolean> get() = _showLoading

    private val _readMore = SingleLiveEvent<Boolean>()
    val readMore: SingleLiveEvent<Boolean> get() = _readMore

    private val _navigateToMap = SingleLiveEvent<Pair<Float, Float>>()
    val navigateToMap: SingleLiveEvent<Pair<Float, Float>> get() = _navigateToMap

    private val _showError = SingleLiveEvent<Boolean>()
    val showError: SingleLiveEvent<Boolean> get() = _showError

    private val adDetailArgs: AdDetailArgs? by lazy {
        savedStateHandle.get<AdDetailArgs>(Constants.AD_DETAIL_ARGS)
    }

    fun onResume() {
        viewModelScope.launch {
            retrieveData()
        }
    }

    private suspend fun retrieveData() = coroutineScope {
        _showLoading.postValue(true)

        val adDetailDeferred = async { getAdDetailUseCase() }
        val adDetailFavoriteDeferred = async { getAdDetailFavoriteUseCase(adDetailArgs?.id ?: 0) }

        val adDetailResult = adDetailDeferred.await()
        val adDetailFavoriteResult = adDetailFavoriteDeferred.await()
            .firstOrNull()

        var adDetail: AdDetail? = null

        adDetailResult.fold(
            onSuccess = {
                adDetail = it
            },
            onFailure = {
                _showError.postValue(true)
                _showLoading.postValue(false)
            }
        )

        val adFavorite: AdFavorite? = adDetailFavoriteResult

        if (adDetailResult.isSuccess) {
            updateLiveDataWithAdsData(adDetail, adFavorite)
        }
    }


    private fun updateLiveDataWithAdsData(adDetail: AdDetail?, adFavorite: AdFavorite?) {
        if (adDetail != null) {
            _adDetail.postValue(AdDetailMapper.mapToVO(adDetail, adDetailArgs))
            _favorite.postValue(adFavorite.toVO())
            _showLoading.postValue(false)
        } else {
            _showError.postValue(true)
            _showLoading.postValue(false)
        }
    }

    fun onFavoriteButtonClicked(isFavorite: Boolean) {
        viewModelScope.launch {
            val id = _adDetail.value?.id
            id?.let {
                val adFavorite = AdFavorite(
                    id = it,
                    isFavorite = isFavorite,
                    date = System.currentTimeMillis()
                )
                if (isFavorite) {
                    saveAdFavoriteUseCase(adFavorite)
                } else {
                    deleteAdFavoriteUseCase(it)
                }
                _favorite.postValue(adFavorite.toVO())
            }
        }
    }

    fun onReadMoreClicked(shouldReadMore: Boolean) {
        _readMore.value = shouldReadMore
    }

    fun onLocationClicked() {
        _navigateToMap.value = Pair(
            _adDetail.value?.latitude ?: 0f,
            _adDetail.value?.longitude ?: 0f
        )
    }
}