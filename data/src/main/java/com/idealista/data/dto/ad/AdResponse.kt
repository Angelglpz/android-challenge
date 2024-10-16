package com.idealista.data.dto.ad

data class AdResponse(
    val propertyCode: String,
    val thumbnail: String,
    val floor: String,
    val price: Int,
    val priceInfo: PriceInfo,
    val propertyType: String,
    val operation: String,
    val size: Int,
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
    val multimedia: Multimedia,
    val features: Features,
    val parkingSpace: ParkingSpace?
)

data class PriceInfo(
    val price: Price
)

data class Price(
    val amount: Int,
    val currencySuffix: String
)

data class Multimedia(
    val images: List<Image>
)

data class Image(
    val url: String,
    val tag: String
)

data class Features(
    val hasAirConditioning: Boolean?,
    val hasBoxRoom: Boolean?,
    val hasSwimmingPool: Boolean?,
    val hasTerrace: Boolean?,
    val hasGarden: Boolean?
)

data class ParkingSpace(
    val hasParkingSpace: Boolean,
    val isParkingSpaceIncludedInPrice: Boolean
)
