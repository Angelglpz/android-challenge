package com.idealista.presentation.feature.ad_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.idealista.presentation.databinding.AdDetailImagePagerItemBinding
import com.idealista.presentation.feature.ad_detail.vo.AdDetailImagesVO

class ImagePagerAdapter(
    private val images: List<AdDetailImagesVO>
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagePagerAdapter.ImageViewHolder {
        val binding = AdDetailImagePagerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagePagerAdapter.ImageViewHolder, position: Int) {
        val imageUrl = images[position % images.size].url
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    inner class ImageViewHolder(private val binding: AdDetailImagePagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.imageViewPagerItem.load(imageUrl)
        }
    }
}