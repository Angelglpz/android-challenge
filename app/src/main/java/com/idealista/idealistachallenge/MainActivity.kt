package com.idealista.idealistachallenge

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.idealista.core.util.Constants
import com.idealista.idealistachallenge.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        /**
         * This listener is used to get the height of the bottom navigation bar when the view is created.
         * This height is then passed to the AdListFragment to calculate the padding for the list.
         */
        binding.bottomNavigation.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.bottomNavigation.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    val bottomNavHeight = binding.bottomNavigation.height
                    val bundle = Bundle().apply {
                        putInt(Constants.NAVIGATION_BAR_HEIGHT_KEY, bottomNavHeight)
                    }

                    navController.setGraph(
                        com.idealista.presentation.R.navigation.nav_graph,
                        bundle
                    )
                    binding.bottomNavigation.setupWithNavController(navController)
                }
            }
        )
    }
}
