package com.idealista.presentation.feature.ad_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idealista.domain.usecase.GetAdDetailUseCase
import com.idealista.domain.util.ApiResponseStatus
import com.idealista.presentation.feature.ad_detail.vo.AdDetailVO
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAdDetailUseCase: GetAdDetailUseCase
) : ViewModel() {

    private val _adDetail = MutableLiveData<AdDetailVO>()
    val adDetail: LiveData<AdDetailVO> get() = _adDetail

    private val adDetailArgs: AdDetailArgs? by lazy {
        savedStateHandle.get<AdDetailArgs>("adDetailArgs")
    }

    init {
        getAdDetail()
    }

    private fun getAdDetail() {
        viewModelScope.launch {
            when (val result = getAdDetailUseCase()) {
                is ApiResponseStatus.Success -> {
                    // TODO()
                }

                is ApiResponseStatus.Error -> {
                    // Handle error
                }
            }
        }
    }
}