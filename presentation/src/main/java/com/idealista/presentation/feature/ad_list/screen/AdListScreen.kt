package com.idealista.presentation.feature.ad_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad.PropertyType
import com.idealista.presentation.R
import com.idealista.presentation.feature.ad_list.event.AdListEvent
import com.idealista.presentation.feature.ad_list.state.AdListScreenState
import com.idealista.presentation.feature.ad_list.util.AdListTheme
import com.idealista.presentation.feature.ad_list.util.Constants
import com.idealista.presentation.feature.ad_list.util.formatNoFraction
import com.idealista.presentation.feature.ad_list.util.formatPrice
import com.idealista.presentation.feature.ad_list.viewmodel.AdListViewModel
import com.idealista.core.R as CoreR

@Composable
fun AdListScreen(
    viewModel: AdListViewModel = hiltViewModel(),
    navigationBarHeight: Float
) {
    AdListTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AdList(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(
                        top = dimensionResource(R.dimen.padding_16dp),
                        bottom = navigationBarHeight.dp
                    ),
                state = viewModel.state,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@Composable
private fun AdList(
    modifier: Modifier = Modifier,
    state: AdListScreenState,
    onEvent: (AdListEvent) -> Unit
) {
    LazyColumn(modifier = modifier) {
        item {
            Column {
                Ad(state, onEvent)
            }
        }
    }
}

@Composable
private fun Ad(state: AdListScreenState, onEvent: (AdListEvent) -> Unit) {
    state.adList.forEach { ad ->
        ElevatedCard(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_8dp))
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            ImageCarousel(ad)
            AdAddress(ad)
            ad.priceInfo.let { price ->
                Text(
                    text = price.amount.formatPrice(price.currencySuffix),
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_8dp)),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(R.dimen.text_size_m).value.sp
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.rooms, ad.rooms),
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_8dp)),
                    fontSize = dimensionResource(R.dimen.text_size_xs).value.sp
                )
                Text(
                    text = stringResource(R.string.size, ad.size.formatNoFraction()),
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_24dp)),
                    fontSize = dimensionResource(R.dimen.text_size_xs).value.sp
                )
                val outerInnerText =
                    if (ad.exterior) stringResource(R.string.outer) else stringResource(R.string.inner)
                val floor = if (ad.floor == "1") stringResource(R.string.ground_floor) else ad.floor
                Text(
                    text = stringResource(
                        R.string.floor_info,
                        floor,
                        outerInnerText
                    ),
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_24dp)),
                    fontSize = dimensionResource(R.dimen.text_size_xs).value.sp
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_8dp)
                )
            )
            AdFooter(state = state, ad = ad, onEvent = onEvent)

        }
    }

}

@Composable
private fun AdFooter(state: AdListScreenState, ad: Ad, onEvent: (AdListEvent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            TextButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.phone_24),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.call),
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.padding_4dp))
                        .align(Alignment.Bottom)
                )
            }

            TextButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.message_24),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.message),
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.padding_4dp))
                        .align(Alignment.Bottom)
                )
            }
        }

        IconButton(onClick = {
            onEvent(AdListEvent.OnFavoritesClicked(ad = ad))
        }) {
            Icon(
                painter = if (ad.isFavorite) {
                    painterResource(id = CoreR.drawable.fill_favorite_24)
                } else {
                    painterResource(id = CoreR.drawable.outline_favorite_border_24)
                },
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun AdAddress(ad: Ad) {
    val fullAddress = when (ad.propertyType) {
        PropertyType.FLAT -> stringResource(R.string.property_type_flat)
    }
    Text(
        text = stringResource(
            R.string.address_complete,
            fullAddress,
            ad.address,
            ad.district,
            ad.municipality
        ),
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.padding_8dp),
            top = dimensionResource(R.dimen.padding_8dp),
            end = dimensionResource(R.dimen.padding_8dp)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = dimensionResource(R.dimen.text_size_s).value.sp
    )
}

@Composable
fun ImageCarousel(ad: Ad) {
    val pagerState = rememberPagerState(pageCount = {
        Constants.IMAGE_PAGER_MAX_SIZE
    })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        val imageIndex = page % ad.multimedia.images.size
        val imageUrl = ad.multimedia.images[imageIndex].url
        Box {
            AsyncImage(
                model = imageUrl,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .background(Color.LightGray.copy(alpha = 0.5f))
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = "${imageIndex + 1}/${ad.multimedia.images.size}",
                    modifier = Modifier
                        .padding(
                            dimensionResource(R.dimen.padding_8dp)
                        )
                )
            }
        }
    }
}


@Preview(apiLevel = 34)
@Composable
fun AdListScreenPreview() {
    AdListTheme {
        AdListScreen(navigationBarHeight = 0f)
    }
}