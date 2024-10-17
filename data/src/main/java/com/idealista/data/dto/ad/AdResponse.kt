package com.idealista.data.dto.ad

data class AdResponse(
    val propertyCode: String,
    val thumbnail: String,
    val floor: String,
    val price: Double,
    val priceInfo: PriceInfoResponse,
    val propertyType: String,
    val operation: String,
    val size: Double,
    val exterior: Boolean,
    val rooms: Int,
    val bathrooms: Int,
    val address: String,
    val province: String,
    val municipality: String,
    val district: String,
    val country: String,
    val neighborhood: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val multimedia: MultimediaResponse,
    val features: FeaturesResponse,
    val parkingSpace: ParkingSpaceResponse?
)

data class PriceInfoResponse(
    val price: PriceResponse
)

data class PriceResponse(
    val amount: Double,
    val currencySuffix: String
)

data class MultimediaResponse(
    val images: List<ImageResponse>
)

data class ImageResponse(
    val url: String,
    val tag: String
)

data class FeaturesResponse(
    val hasAirConditioning: Boolean?,
    val hasBoxRoom: Boolean?,
    val hasSwimmingPool: Boolean?,
    val hasTerrace: Boolean?,
    val hasGarden: Boolean?
)

data class ParkingSpaceResponse(
    val hasParkingSpace: Boolean,
    val isParkingSpaceIncludedInPrice: Boolean
)
