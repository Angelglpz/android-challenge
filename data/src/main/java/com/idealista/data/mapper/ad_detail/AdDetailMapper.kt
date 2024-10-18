package com.idealista.data.mapper.ad_detail

import com.idealista.data.dto.ad_detail.AdDetailResponse
import com.idealista.data.dto.ad_detail.EnergyCertificationDetailResponse
import com.idealista.data.dto.ad_detail.EnergyValueDetailResponse
import com.idealista.data.dto.ad_detail.ImageDetailResponse
import com.idealista.data.dto.ad_detail.MoreCharacteristicsDetailResponse
import com.idealista.data.dto.ad_detail.MultimediaDetailResponse
import com.idealista.data.dto.ad_detail.PriceInfoDetailResponse
import com.idealista.data.dto.ad_detail.UbicationDetailResponse
import com.idealista.domain.model.ad.PropertyType
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.model.ad_detail.EnergyCertificationDetail
import com.idealista.domain.model.ad_detail.EnergyValueDetail
import com.idealista.domain.model.ad_detail.ImageDetail
import com.idealista.domain.model.ad_detail.MoreCharacteristicsDetail
import com.idealista.domain.model.ad_detail.MultimediaDetail
import com.idealista.domain.model.ad_detail.PriceInfoDetail
import com.idealista.domain.model.ad_detail.UbicationDetail
import java.util.Locale

fun AdDetailResponse.toDomain(): AdDetail = AdDetail(
    id = this.id,
    price = this.price,
    priceInfo = this.priceInfo.toDomain(),
    operation = this.operation,
    propertyType = PropertyType.valueOf(this.propertyType.uppercase(Locale.getDefault())),
    extendedPropertyType = this.extendedPropertyType,
    homeType = this.homeType,
    state = this.state,
    multimedia = this.multimedia.toDomain(),
    propertyComment = this.propertyComment,
    ubication = this.ubication.toDomain(),
    country = this.country,
    moreCharacteristics = this.moreCharacteristics.toDomain(),
    energyCertification = this.energyCertification.toDomain()
)


fun PriceInfoDetailResponse.toDomain(): PriceInfoDetail = PriceInfoDetail(
    amount = this.amount,
    currencySuffix = this.currencySuffix
)


fun MultimediaDetailResponse.toDomain(): MultimediaDetail = MultimediaDetail(
    images = this.images.map { it.toDomain() }
)


fun ImageDetailResponse.toDomain(): ImageDetail = ImageDetail(
    url = this.url,
    tag = this.tag,
    localizedName = this.localizedName,
    multimediaId = this.multimediaId
)


fun UbicationDetailResponse.toDomain(): UbicationDetail = UbicationDetail(
    latitude = this.latitude,
    longitude = this.longitude
)


fun MoreCharacteristicsDetailResponse.toDomain(): MoreCharacteristicsDetail =
    MoreCharacteristicsDetail(
        communityCosts = this.communityCosts,
        roomNumber = this.roomNumber,
        bathNumber = this.bathNumber,
        exterior = this.exterior,
        housingFurnitures = this.housingFurnitures,
        agencyIsABank = this.agencyIsABank,
        energyCertificationType = this.energyCertificationType,
        flatLocation = this.flatLocation,
        modificationDate = this.modificationDate,
        constructedArea = this.constructedArea,
        lift = this.lift,
        boxroom = this.boxroom,
        isDuplex = this.isDuplex,
        floor = this.floor,
        status = this.status
    )


fun EnergyCertificationDetailResponse.toDomain(): EnergyCertificationDetail =
    EnergyCertificationDetail(
        title = this.title,
        energyConsumption = this.energyConsumption.toDomain(),
        emissions = this.emissions.toDomain()
    )


fun EnergyValueDetailResponse.toDomain(): EnergyValueDetail = EnergyValueDetail(
    type = this.type
)

