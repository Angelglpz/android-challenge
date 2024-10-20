package com.idealista.presentation.feature.ad_detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.idealista.presentation.R
import com.idealista.presentation.databinding.AdDetailMapFragmentBinding
import com.idealista.presentation.feature.ad_detail.util.Constants

class AdDetailMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: AdDetailMapFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdDetailMapFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.closeButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latitude = arguments?.getDouble(Constants.LATITUDE_KEY) ?: 0.0
        val longitude = arguments?.getDouble(Constants.LONGITUDE_KEY) ?: 0.0
        val coordinates = LatLng(latitude, longitude)
        val marker = MarkerOptions().position(coordinates)
        googleMap.addMarker(marker)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, Constants.MAP_ZOOM))
    }
}