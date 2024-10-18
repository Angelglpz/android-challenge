package com.idealista.presentation.feature.ad_list.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idealista.presentation.feature.ad_list.screen.AdListScreen
import com.idealista.presentation.util.Constants
import com.idealista.presentation.util.pxToDp

class AdListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        val navigationBarHeight =
            arguments?.getInt(Constants.NAVIGATION_BAR_HEIGHT_KEY)?.pxToDp(requireContext())
        return ComposeView(requireContext()).apply {
            setContent {
                AdListScreen(
                    navController = navController,
                    navigationBarHeight = navigationBarHeight ?: 0F
                )
            }
        }
    }
}