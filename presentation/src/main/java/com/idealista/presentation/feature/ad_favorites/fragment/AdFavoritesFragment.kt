package com.idealista.presentation.feature.ad_favorites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idealista.presentation.databinding.AdFavoritesListFragmentBinding
import com.idealista.presentation.feature.ad_favorites.adapter.AdFavoritesAdapter
import com.idealista.presentation.feature.ad_favorites.viewmodel.AdFavoritesViewModel
import com.idealista.presentation.util.gone
import com.idealista.presentation.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdFavoritesFragment : Fragment() {

    private lateinit var binding: AdFavoritesListFragmentBinding
    private val viewModel: AdFavoritesViewModel by viewModels()

    private var adFavoritesAdapter: AdFavoritesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdFavoritesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewModel.adFavorites.observe(viewLifecycleOwner) { adFavorites ->
            adFavoritesAdapter?.submitList(adFavorites)
        }
        viewModel.showLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingView.root.visible()
            } else {
                binding.loadingView.root.gone()
            }
        }
    }

    private fun setAdapter() {
        binding.favoritesListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adFavoritesAdapter = AdFavoritesAdapter(
            resources = resources,
            onFavoriteButtonClicked = { adId ->
                viewModel.onFavoriteButtonClicked(adId)
                adFavoritesAdapter?.removeItem(adId)
            },
            onAdClicked = { adDetailArgs ->
                findNavController().navigate(
                    AdFavoritesFragmentDirections.actionAdFavoritesFragmentToAdDetailFragment(
                        adDetailArgs
                    )
                )
            }
        )
        binding.favoritesListRecyclerView.adapter = adFavoritesAdapter
    }
}