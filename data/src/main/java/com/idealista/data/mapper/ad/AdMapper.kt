package com.idealista.data.mapper.ad

import com.idealista.data.dto.ad.AdResponse
import com.idealista.data.dto.ad.FeaturesResponse
import com.idealista.data.dto.ad.ImageResponse
import com.idealista.data.dto.ad.MultimediaResponse
import com.idealista.data.dto.ad.ParkingSpaceResponse
import com.idealista.data.dto.ad.PriceResponse
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad.Features
import com.idealista.domain.model.ad.Image
import com.idealista.domain.model.ad.Multimedia
import com.idealista.domain.model.ad.Operation
import com.idealista.domain.model.ad.ParkingSpace
import com.idealista.domain.model.ad.Price
import com.idealista.domain.model.ad.PropertyType
import java.util.Locale

fun AdResponse.toDomain(): Ad = Ad(
    id = this.propertyCode.toInt(),
    thumbnail = this.thumbnail,
    floor = this.floor,
    price = this.price,
    priceInfo = this.priceInfo.price.toDomain(),
    propertyType = PropertyType.valueOf(this.propertyType.uppercase(Locale.getDefault())),
    operation = Operation.valueOf(this.operation.uppercase(Locale.getDefault())),
    size = this.size,
    exterior = this.exterior,
    rooms = this.rooms,
    bathrooms = this.bathrooms,
    address = this.address,
    province = this.province,
    municipality = this.municipality,
    district = this.district,
    country = this.country,
    neighborhood = this.neighborhood,
    latitude = this.latitude,
    longitude = this.longitude,
    description = this.description,
    multimedia = this.multimedia.toDomain(),
    features = this.features.toDomain(),
    parkingSpace = this.parkingSpace?.toDomain()
)


fun PriceResponse.toDomain(): Price = Price(
    amount = this.amount,
    currencySuffix = this.currencySuffix
)

fun MultimediaResponse.toDomain(): Multimedia = Multimedia(
    images = this.images.map { it.toDomain() }
)


fun ImageResponse.toDomain(): Image = Image(
    url = this.url,
    tag = this.tag
)


fun FeaturesResponse.toDomain(): Features {
    return Features(
        hasAirConditioning = this.hasAirConditioning,
        hasBoxRoom = this.hasBoxRoom,
        hasSwimmingPool = this.hasSwimmingPool,
        hasTerrace = this.hasTerrace,
        hasGarden = this.hasGarden
    )
}

fun ParkingSpaceResponse.toDomain(): ParkingSpace = ParkingSpace(
    hasParkingSpace = this.hasParkingSpace,
    isParkingSpaceIncludedInPrice = this.isParkingSpaceIncludedInPrice
)

