package com.idealista.presentation.feature.ad_favorites.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.idealista.presentation.R
import com.idealista.presentation.databinding.AdFavoritesItemBinding
import com.idealista.presentation.feature.ad_favorites.vo.AdFavoriteListVO
import com.idealista.presentation.feature.navigation.ad_detail.AdDetailArgs
import com.idealista.presentation.util.getResourceString

class AdFavoritesAdapter(
    private val resources: Resources,
    private val onFavoriteButtonClicked: (Int) -> Unit,
    private val onAdClicked: (AdDetailArgs) -> Unit
) :
    ListAdapter<AdFavoriteListVO, AdFavoritesAdapter.AdViewHolder>(AdDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val view =
            AdFavoritesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        val ad = getItem(position)
        val fullAddressFormatted = resources.getString(
            R.string.address_complete,
            resources.getString(
                ad.propertyType.getResourceString()
            ),
            ad.address,
            ad.district,
            ad.municipality

        )
        holder.bind(ad, fullAddressFormatted)
    }

    fun removeItem(id: Int) {
        val list = currentList.toMutableList()
        val index = list.indexOfFirst { it.id == id }
        list.removeAt(index)
        submitList(list)
    }

    inner class AdViewHolder(private val binding: AdFavoritesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ad: AdFavoriteListVO, fullAddress: String) {
            binding.apply {
                adCard.setOnClickListener {
                    onAdClicked(
                        AdDetailArgs(
                            id = ad.id,
                            address = ad.address,
                            district = ad.district,
                            municipality = ad.municipality
                        )
                    )
                }
                thumbnailImage.load(ad.thumbnail)
                priceText.text = ad.price
                addressText.text = fullAddress
                priceText.text = ad.price
                roomsText.text = ad.rooms
                sizeText.text = ad.size
                floorInfoText.text = ad.floor
                favoriteImageButton.setOnClickListener {
                    favoriteImageButton.setImageResource(R.drawable.outline_favorite_border_24)
                    onFavoriteButtonClicked(ad.id)
                }
                favoriteText.text = ad.favorite
                favoriteDateText.text = ad.dateFavorite
                favoriteImageButton.setImageResource(R.drawable.fill_favorite_24)
            }
        }
    }

    private object AdDiffCallback : DiffUtil.ItemCallback<AdFavoriteListVO>() {
        override fun areItemsTheSame(
            oldItem: AdFavoriteListVO,
            newItem: AdFavoriteListVO
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AdFavoriteListVO,
            newItem: AdFavoriteListVO
        ): Boolean {
            return oldItem == newItem
        }
    }
}