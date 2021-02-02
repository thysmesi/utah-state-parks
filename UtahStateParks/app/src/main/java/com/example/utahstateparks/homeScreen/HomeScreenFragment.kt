package com.example.utahstateparks.homeScreen

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration

import com.example.utahstateparks.R
import com.example.utahstateparks.databinding.HomeScreenFragmentBinding
import com.google.android.material.navigation.NavigationView


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

        binding.homeScreenViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToBoatingInfo.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToBoatingInfoFragment())

                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToAtvInfo.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToAtvInfoFragment())

                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToPasses.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToParkPassesFragment())

                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToParks.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToParksSelectorFragment())

                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToMenu.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                //binding.navDrawer.openDrawer(Gravity.LEFT)
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }
}