package com.idealista.domain.model.ad_detail

data class AdDetail(
    val adId: Int,
    val price: Double,
    val priceInfo: PriceInfoDetail,
    val operation: String,
    val propertyType: String,
    val extendedPropertyType: String,
    val homeType: String,
    val state: String,
    val multimedia: MultimediaDetail,
    val propertyComment: String,
    val ubication: UbicationDetail,
    val country: String,
    val moreCharacteristics: MoreCharacteristicsDetail,
    val energyCertification: EnergyCertificationDetail
)

data class PriceInfoDetail(
    val amount: Double,
    val currencySuffix: String
)

data class MultimediaDetail(
    val images: List<ImageDetail>
)

data class ImageDetail(
    val url: String,
    val tag: String,
    val localizedName: String,
    val multimediaId: Long
)

data class UbicationDetail(
    val latitude: Double,
    val longitude: Double
)

data class MoreCharacteristicsDetail(
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

data class EnergyCertificationDetail(
    val title: String,
    val energyConsumption: EnergyValueDetail,
    val emissions: EnergyValueDetail
)

data class EnergyValueDetail(
    val type: String
)
