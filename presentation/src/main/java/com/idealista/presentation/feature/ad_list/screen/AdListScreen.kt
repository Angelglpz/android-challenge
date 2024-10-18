package com.idealista.presentation.feature.ad_list.screen

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.idealista.domain.model.ad.Ad
import com.idealista.presentation.R
import com.idealista.presentation.feature.ad_list.event.AdListEvent
import com.idealista.presentation.feature.ad_list.fragment.AdListFragmentDirections
import com.idealista.presentation.feature.ad_list.state.AdListScreenState
import com.idealista.presentation.feature.ad_list.util.AdListTheme
import com.idealista.presentation.feature.ad_list.util.formatNoFraction
import com.idealista.presentation.feature.ad_list.util.formatPrice
import com.idealista.presentation.feature.ad_list.viewmodel.AdListViewModel
import com.idealista.presentation.util.getResourceString

@Composable
fun AdListScreen(
    viewModel: AdListViewModel = hiltViewModel(),
    navController: NavController,
    navigationBarHeight: Float
) {
    AdListTheme {
        if (viewModel.state.showLoading) {
            Box {
                AndroidView(
                    factory = { context ->
                        LayoutInflater.from(context).inflate(R.layout.loading_view, null, false)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AdList(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(
                            top = dimensionResource(R.dimen.dimen_16dp),
                            bottom = navigationBarHeight.dp
                        ),
                    state = viewModel.state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
    LaunchedEffect(viewModel.state.navigateToDetailWithArgs) {
        if (viewModel.state.navigateToDetailWithArgs != null) {
            val action =
                AdListFragmentDirections.actionAdListFragmentToAdDetailFragment(viewModel.state.navigateToDetailWithArgs)
            navController.navigate(action)
            viewModel.cleanNavigateToDetail()
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
                .padding(dimensionResource(R.dimen.dimen_8dp))
                .background(color = MaterialTheme.colorScheme.background)
                .clickable {
                    onEvent(AdListEvent.OnAdClicked(ad))
                }
        ) {
            ImageCarousel(ad)
            AdAddress(ad)
            ad.priceInfo.let { price ->
                Text(
                    text = price.amount.formatPrice(price.currencySuffix),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.dimen_8dp),
                        top = dimensionResource(R.dimen.dimen_4dp)
                    ),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            AdMoreInfo(ad)

            HorizontalDivider(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.dimen_8dp)
                )
            )
            AdFooter(state = state, ad = ad, onEvent = onEvent)

        }
    }

}

@Composable
fun ImageCarousel(ad: Ad) {
    val totalImages = ad.multimedia.images.size
    val pagerState = rememberPagerState(pageCount = {
        Int.MAX_VALUE
    })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        // We use the modulo function here to make sure that the page is always between 0 and the number of images
        val imageIndex = page % totalImages
        val imageUrl = ad.multimedia.images[imageIndex].url
        Box {
            AsyncImage(
                model = imageUrl,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )


            // Calculate the remainder of dividing half by the total number of images
            val half = Int.MAX_VALUE / 2
            val halfMod = half % totalImages

            LaunchedEffect(pagerState) {
                // Scroll the pager to the calculated page to simulate infinite scrolling
                pagerState.scrollToPage(half - halfMod)
            }
            Box(
                modifier = Modifier
                    .background(Color.LightGray.copy(alpha = 0.5f))
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = "${imageIndex + 1}/${ad.multimedia.images.size}",
                    modifier = Modifier
                        .padding(
                            dimensionResource(R.dimen.dimen_8dp)
                        ),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
private fun AdAddress(ad: Ad) {
    Text(
        text = stringResource(
            R.string.address_complete,
            stringResource(ad.propertyType.getResourceString()),
            ad.address,
            ad.district,
            ad.municipality
        ),
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.dimen_8dp),
            top = dimensionResource(R.dimen.dimen_8dp),
            end = dimensionResource(R.dimen.dimen_8dp)
        ),
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun AdMoreInfo(ad: Ad) {
    Row(modifier = Modifier.padding(top = dimensionResource(R.dimen.dimen_4dp))) {
        Text(
            text = stringResource(R.string.rooms, ad.rooms),
            modifier = Modifier.padding(start = dimensionResource(R.dimen.dimen_8dp)),
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = stringResource(R.string.size, ad.size.formatNoFraction()),
            modifier = Modifier.padding(start = dimensionResource(R.dimen.dimen_24dp)),
            style = MaterialTheme.typography.bodySmall
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
            modifier = Modifier.padding(start = dimensionResource(R.dimen.dimen_24dp)),
            style = MaterialTheme.typography.bodySmall
        )
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
                        .padding(start = dimensionResource(R.dimen.dimen_4dp))
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
                        .padding(start = dimensionResource(R.dimen.dimen_4dp))
                        .align(Alignment.Bottom)
                )
            }
        }

        IconButton(onClick = {
            onEvent(AdListEvent.OnFavoritesClicked(ad = ad))
        }) {
            Icon(
                painter = if (ad.isFavorite) {
                    painterResource(id = R.drawable.fill_favorite_24)
                } else {
                    painterResource(id = R.drawable.outline_favorite_border_24)
                },
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }
}


@Preview(apiLevel = 34)
@Composable
fun AdListScreenPreview() {
    AdListTheme {
        AdListScreen(navController = rememberNavController(), navigationBarHeight = 0f)
    }
}