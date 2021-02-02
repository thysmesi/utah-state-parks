package com.example.utahstateparks.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.utahstateparks.R
import com.example.utahstateparks.databinding.HomeScreenFragmentBinding


class HomeScreenFragment : Fragment() {

    companion object {
        fun newInstance() = HomeScreenFragment()
    }

    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        val binding: HomeScreenFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.home_screen_fragment, container, false)

        val application = requireNotNull(this.activity).application

        binding.homeScreenViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToBoatingInfo.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToBoatingInfoFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToAtvInfo.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToAtvInfoFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToPasses.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToParkPassesFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToParks.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToParksSelectorFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }
}