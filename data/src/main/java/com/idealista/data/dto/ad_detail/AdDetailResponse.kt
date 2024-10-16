package com.idealista.data.dto.ad_detail

data class AdDetailResponse(
    val adid: Int,
    val price: Double,
    val priceInfo: PriceInfo,
    val operation: String,
    val propertyType: String,
    val extendedPropertyType: String,
    val homeType: String,
    val state: String,
    val multimedia: Multimedia,
    val propertyComment: String,
    val ubication: Ubication,
    val country: String,
    val moreCharacteristics: MoreCharacteristics,
    val energyCertification: EnergyCertification
)

data class PriceInfo(
    val amount: Double,
    val currencySuffix: String
)

data class Multimedia(
    val images: List<Image>
)

data class Image(
    val url: String,
    val tag: String,
    val localizedName: String,
    val multimediaId: Long
)

data class Ubication(
    val latitude: Double,
    val longitude: Double
)

data class MoreCharacteristics(
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

data class EnergyCertification(
    val title: String,
    val energyConsumption: EnergyValue,
    val emissions: EnergyValue
)

data class EnergyValue(
    val type: String
)
