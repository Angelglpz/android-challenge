package com.idealista.presentation.feature.ad_list.screen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.usecase.ad_favorite.SaveAdFavoriteUseCase
import com.idealista.domain.usecase.ad_list.GetAdsListUseCase
import com.idealista.presentation.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class AdListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // Mockeamos los casos de uso (UseCases)
    @Inject
    lateinit var getAdsListUseCase: GetAdsListUseCase

    @Inject
    lateinit var adListUseCase2: SaveAdFavoriteUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_ad_list_screen_shows_data_from_two_usecases() = runTest {
        val priceDouble = 300.00
        val currencySuffix = "€"
        coEvery { getAdsListUseCase() } returns Result.success(
            listOf(
                mockk<Ad>(relaxed = true) {
                    every { priceInfo.amount } returns priceDouble
                    every { priceInfo.currencySuffix } returns currencySuffix
                },
            )
        )

        composeTestRule.setContent {
            AdListScreen(mockk(), mockk())
        }

        composeTestRule.onNodeWithText("300 €").assertExists()
    }
}
