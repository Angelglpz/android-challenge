package com.idealista.presentation.feature.ad_detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.idealista.presentation.R
import com.idealista.presentation.databinding.AdDetailFragmentBinding
import com.idealista.presentation.feature.ad_detail.adapter.ImagePagerAdapter
import com.idealista.presentation.feature.ad_detail.viewmodel.AdDetailViewModel
import com.idealista.presentation.feature.ad_detail.vo.AdDetailVO
import com.idealista.presentation.util.formatNoFraction
import com.idealista.presentation.util.getResourceString
import com.idealista.presentation.util.gone
import com.idealista.presentation.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdDetailFragment : Fragment() {

    private lateinit var binding: AdDetailFragmentBinding
    private val viewModel: AdDetailViewModel by viewModels()

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
    }

    private fun setObservers() {
        viewModel.adDetail.observe(viewLifecycleOwner) { ad ->
            binding.apply {
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
                setUpViewPager(ad)
            }
        }

        viewModel.showLoading.observe(viewLifecycleOwner) { showLoading ->
            if (showLoading) {
                binding.loadingView.root.visible()
            } else {
                binding.loadingView.root.gone()
            }
        }
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
}