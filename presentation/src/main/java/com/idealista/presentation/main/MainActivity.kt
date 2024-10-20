package com.idealista.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.idealista.presentation.R
import com.idealista.presentation.databinding.MainActivityBinding
import com.idealista.presentation.util.gone
import com.idealista.presentation.util.visible
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
        navController.setGraph(R.navigation.nav_graph)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.adDetailFragment, R.id.adDetailMapFragment -> {
                    binding.bottomNavigation.gone()
                }

                R.id.adListFragment -> {
                    onHomeSelected()
                    binding.bottomNavigation.visible()
                }

                R.id.adFavoritesFragment -> {
                    onFavoritesSelected()
                    binding.bottomNavigation.visible()
                }

                else -> {
                    binding.bottomNavigation.visible()
                }
            }
        }
    }

    private fun onHomeSelected() {
        binding.bottomNavigation.apply {
            menu.findItem(R.id.adListFragment).icon = ResourcesCompat.getDrawable(
                resources,
                R.drawable.fill_home_24,
                null
            )
            menu.findItem(R.id.adFavoritesFragment).icon = ResourcesCompat.getDrawable(
                resources,
                R.drawable.outline_favorite_border_24,
                null
            )
        }
    }

    private fun onFavoritesSelected() {
        binding.bottomNavigation.apply {
            menu.findItem(R.id.adListFragment).icon = ResourcesCompat.getDrawable(
                resources,
                R.drawable.outline_home_24,
                null
            )
            menu.findItem(R.id.adFavoritesFragment).icon = ResourcesCompat.getDrawable(
                resources,
                R.drawable.fill_favorite_24,
                null
            )
        }
    }
}
