package com.example.utahstateparks.ui.parkSelector

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utahstateparks.R
import com.example.utahstateparks.data.AppDatabase
import com.example.utahstateparks.databinding.ParkSelectorFragmentBinding

class ParkSelectorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).stateParkDao()
        val viewModelFactory = ParkSelectorViewModelFactory(dataSource, application)

        val parkSelectorViewModel = ViewModelProvider(this, viewModelFactory).get(ParkSelectorViewModel::class.java)

        val binding: ParkSelectorFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.park_selector_fragment, container, false)

        binding.parkSelectorViewModel = parkSelectorViewModel
        binding.lifecycleOwner = this

        parkSelectorViewModel.navigateToPark.observe(viewLifecycleOwner, Observer { park ->
            park?.let {
                this.findNavController().navigate(
                    ParkSelectorFragmentDirections.actionParkSelectorFragmentToStateParkFragment(park))
                parkSelectorViewModel.onParkNavigated()
            }
        })


        val manager = LinearLayoutManager(activity)
        binding.parkList.layoutManager = manager

        val adapter = ParkSelectorAdapter(ParkSelectorListener { parkId ->
            parkSelectorViewModel.onParkClicked(parkId)
        }, StarClickListener { park ->
            parkSelectorViewModel.onFavoriteClicked(park)
        })
        binding.parkList.adapter = adapter

        parkSelectorViewModel.parks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }
}