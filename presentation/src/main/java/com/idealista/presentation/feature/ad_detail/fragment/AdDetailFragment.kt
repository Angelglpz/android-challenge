package com.idealista.presentation.feature.ad_detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.idealista.presentation.databinding.AdDetailFragmentBinding
import com.idealista.presentation.feature.ad_detail.viewmodel.AdDetailViewModel
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

            }
        }
    }
}