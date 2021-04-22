package com.example.utahstateparks.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.utahstateparks.R
import com.example.utahstateparks.data.AppDatabase
import com.example.utahstateparks.databinding.MapFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    lateinit var mapViewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MapFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.map_fragment, container, false
        )


        val application = requireNotNull(this.activity).application
        val arguments = MapFragmentArgs.fromBundle(requireArguments())

        val dataSource = AppDatabase.getInstance(application).stateParkDao()
        val viewModelFactory = MapViewModelFactory(arguments.stateParkKey, dataSource)

        mapViewModel = ViewModelProvider(this, viewModelFactory).get(MapViewModel::class.java)

        binding.mapViewModel = mapViewModel
        binding.lifecycleOwner = this

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapViewModel.getPark().observe(viewLifecycleOwner, Observer { park ->
            park?.let {
                val mMap = googleMap

                val destination = LatLng(park.latitude, park.longitude)
                mMap.addMarker(MarkerOptions().position(destination).title("Marker"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 11.0f))
            }
        })
    }

}