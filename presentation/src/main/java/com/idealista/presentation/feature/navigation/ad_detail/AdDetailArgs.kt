package com.idealista.presentation.feature.navigation.ad_detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdDetailArgs(
    val adId: String,
    val address: String,
    val district: String,
    val municipality: String
) : Parcelable
