package com.idealista.presentation.feature.ad_list.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.idealista.presentation.R
import com.idealista.presentation.feature.ad_list.util.AdListTheme
import com.idealista.core.R as CoreR

@Composable
fun AdListScreen(navigationBarHeight: Float) {
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
                    )
            )
        }
    }
}

@Composable
fun AdList(
    modifier: Modifier = Modifier,
    onAdClick: (Int) -> Unit = {}
) {
    val list = (0..10).toList()
    LazyColumn(modifier = modifier) {
        item {
            Column {
                list.forEach {
                    Ad()
                }
            }
        }
    }
}

@Composable
fun Ad() {
    ElevatedCard(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_8dp))
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = com.idealista.core.R.drawable.outline_home_24),
            contentDescription = null
        )
        Text(
            text = "Ad",
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_8dp))
        )
        Text(
            text = "695.500 €",
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_8dp))
        )
        Row {
            Text(
                text = "3 rooms",
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_8dp))
            )
            Text(
                text = "100 m2",
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_24dp))
            )
            Text(
                text = "ascensor",
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_24dp))
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.padding_16dp)
            )
        )
        AdFooter()

    }

}

@Composable
fun AdFooter() {
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
                    text = "Llamar",
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
                    text = "Contactar",
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.padding_4dp))
                        .align(Alignment.Bottom)
                )
            }
        }

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = CoreR.drawable.outline_favorite_border_24),
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
        AdListScreen(0F)
    }
}