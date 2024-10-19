package com.idealista.presentation.feature.ad_detail.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.color.MaterialColors
import com.idealista.presentation.R
import com.idealista.presentation.databinding.AdDetailFragmentBinding
import com.idealista.presentation.feature.ad_detail.adapter.ImagePagerAdapter
import com.idealista.presentation.feature.ad_detail.mapper.mapCharacteristicsToText
import com.idealista.presentation.feature.ad_detail.viewmodel.AdDetailViewModel
import com.idealista.presentation.feature.ad_detail.vo.AdDetailFavoriteVO
import com.idealista.presentation.feature.ad_detail.vo.AdDetailVO
import com.idealista.presentation.util.formatNoFraction
import com.idealista.presentation.util.getResourceString
import com.idealista.presentation.util.gone
import com.idealista.presentation.util.visible
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.material.R as MaterialR

@AndroidEntryPoint
class AdDetailFragment : Fragment() {

    private lateinit var binding: AdDetailFragmentBinding
    private val viewModel: AdDetailViewModel by viewModels()

    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.favoriteImageButton.setOnClickListener {
            viewModel.onFavoriteButtonClicked(!isFavorite)
        }

        binding.propertyCommentReadMoreText.setOnClickListener {
            viewModel.onReadMoreClicked(binding.propertyCommentCollapsedText.visibility == View.VISIBLE)
        }
    }

    private fun setObservers() {
        viewModel.adDetail.observe(viewLifecycleOwner) { ad ->
            binding.apply {
                setUpViewPager(ad)
                addressText.text = getString(
                    R.string.address_with_type,
                    getString(ad.propertyType.getResourceString()),
                    ad.address
                )
                districtText.text = ad.addressLocation
                priceText.text = ad.price
                currencyText.text = ad.currency
                roomsText.text = getString(R.string.rooms, ad.rooms)
                sizeText.text = getString(R.string.size, ad.size.toDouble().formatNoFraction())
                val outerInnerText =
                    if (ad.exterior) getString(R.string.outer) else getString(R.string.inner)
                val floor = if (ad.floor == "1") getString(R.string.ground_floor) else ad.floor
                floorInfoText.text = getString(R.string.floor_info, floor, outerInnerText)
                propertyCommentTitleText.text = getString(R.string.property_comment_title)
                propertyCommentReadMoreText.text = getString(R.string.read_more)
                propertyCommentCollapsedText.text = ad.commentary
                propertyCommentExpandedText.text = ad.commentary
                basicCharacteristicsTitleText.text = getString(R.string.basic_characteristics_title)
                setUpCharacteristics(ad)
            }
        }

        viewModel.favorite.observe(viewLifecycleOwner) {
            isFavorite = it?.isFavorite ?: false
            manageFavoriteButton(it)
        }

        viewModel.showLoading.observe(viewLifecycleOwner) { showLoading ->
            if (showLoading) {
                binding.loadingView.root.visible()
            } else {
                binding.loadingView.root.gone()
            }
        }

        viewModel.readMore.observe(viewLifecycleOwner) { readMore ->
            binding.apply {
                if (readMore) {
                    propertyCommentExpandedText.visible()
                    propertyCommentCollapsedText.gone()
                    propertyCommentReadMoreText.text = getString(R.string.read_less)
                } else {
                    propertyCommentCollapsedText.visible()
                    propertyCommentExpandedText.gone()
                    propertyCommentReadMoreText.text = getString(R.string.read_more)
                }

            }

        }
    }

    private fun setUpCharacteristics(ad: AdDetailVO) {
        val characteristicsList = ad.basicCharacteristics.mapCharacteristicsToText(resources)
        val formattedText = characteristicsList.joinToString("\n• ", prefix = "• ")
        binding.basicCharacteristicsText.text = formattedText

    }

    private fun setUpViewPager(ad: AdDetailVO) {
        val pagerAdapter = ImagePagerAdapter(ad.images)
        binding.apply {
            val half = Int.MAX_VALUE / 2
            val halfMod = half % ad.images.size
            imageViewPager.adapter = pagerAdapter
            imageViewPager.setCurrentItem(half - halfMod, false)
            imageViewPagerPageText.text = getString(R.string.page_count, 1, ad.images.size)
            imageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val imageIndex = position % ad.images.size
                    imageViewPagerPageText.text =
                        getString(R.string.page_count, imageIndex + 1, ad.images.size)
                }
            })
        }
    }

    private fun manageFavoriteButton(adDetailFavoriteVO: AdDetailFavoriteVO) {
        binding.apply {
            favoriteImageButton.setImageResource(
                if (adDetailFavoriteVO.isFavorite) R.drawable.fill_favorite_24 else R.drawable.outline_favorite_border_24
            )
            favoriteText.text = getString(
                if (adDetailFavoriteVO.isFavorite) R.string.remove_favorite else R.string.add_favorite
            )
            if (adDetailFavoriteVO.isFavorite) {
                favoriteDateText.text = getString(R.string.favorite_date, adDetailFavoriteVO.date)
                favoriteDateText.visible()
            } else {
                favoriteDateText.gone()
            }
            favoriteText.apply {
                if (adDetailFavoriteVO.isFavorite) {
                    setTextColor(
                        MaterialColors.getColor(
                            requireContext(),
                            MaterialR.attr.colorPrimary,
                            Color.BLACK
                        )
                    )
                } else {
                    setTextColor(
                        MaterialColors.getColor(
                            requireContext(),
                            MaterialR.attr.titleTextColor,
                            Color.BLACK
                        )
                    )
                }
            }

        }
    }
}