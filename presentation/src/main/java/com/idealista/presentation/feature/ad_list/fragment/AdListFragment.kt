package com.idealista.presentation.feature.ad_list.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.idealista.core.util.Constants
import com.idealista.core.util.pxToDp
import com.idealista.presentation.feature.ad_list.screen.AdListScreen

class AdListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navigationBarHeight =
            arguments?.getInt(Constants.NAVIGATION_BAR_HEIGHT_KEY)?.pxToDp(requireContext())
        return ComposeView(requireContext()).apply {
            setContent {
                AdListScreen(navigationBarHeight = navigationBarHeight ?: 0F)
            }
        }
    }
}