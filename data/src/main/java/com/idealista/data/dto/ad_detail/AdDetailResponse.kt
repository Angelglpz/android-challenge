package com.idealista.data.dto.ad_detail

import com.squareup.moshi.Json

data class AdDetailResponse(
    @Json(name = "adid") val id: Int,
    val price: Double,
    val priceInfo: PriceInfoDetailResponse,
    val operation: String,
    val propertyType: String,
    val extendedPropertyType: String,
    val homeType: String,
    val state: String,
    val multimedia: MultimediaDetailResponse,
    val propertyComment: String,
    val ubication: UbicationDetailResponse,
    val country: String,
    val moreCharacteristics: MoreCharacteristicsDetailResponse,
    val energyCertification: EnergyCertificationDetailResponse
)

data class PriceInfoDetailResponse(
    val amount: Double,
    val currencySuffix: String
)

data class MultimediaDetailResponse(
    val images: List<ImageDetailResponse>
)

data class ImageDetailResponse(
    val url: String,
    val tag: String,
    val localizedName: String,
    val multimediaId: Long
)

data class UbicationDetailResponse(
    val latitude: Double,
    val longitude: Double
)

data class MoreCharacteristicsDetailResponse(
    val communityCosts: Double,
    val roomNumber: Int,
    val bathNumber: Int,
    val exterior: Boolean,
    val housingFurnitures: String,
    val agencyIsABank: Boolean,
    val energyCertificationType: String,
    val flatLocation: String,
    val modificationDate: Long,
    val constructedArea: Int,
    val lift: Boolean,
    val boxroom: Boolean,
    val isDuplex: Boolean,
    val floor: String,
    val status: String
)

data class EnergyCertificationDetailResponse(
    val title: String,
    val energyConsumption: EnergyValueDetailResponse,
    val emissions: EnergyValueDetailResponse
)

data class EnergyValueDetailResponse(
    val type: String
)
