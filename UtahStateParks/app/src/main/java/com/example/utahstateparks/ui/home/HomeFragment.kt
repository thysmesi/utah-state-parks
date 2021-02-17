package com.example.utahstateparks.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.utahstateparks.R
import com.example.utahstateparks.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.home_fragment, container, false)

        val homeViewModel = HomeViewModel()

        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        homeViewModel.navigateToParkSelector.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToParkSelectorFragment())
                homeViewModel.doneNavigating()
            }
        })
        homeViewModel.navigateToAtvInfo.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToAtvInfoFragment())
                homeViewModel.doneNavigating()
            }
        })
        homeViewModel.navigateToBoatingInfo.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToBoatingInfoFragment())
                homeViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}