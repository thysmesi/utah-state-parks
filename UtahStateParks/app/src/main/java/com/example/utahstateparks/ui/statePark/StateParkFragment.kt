package com.example.utahstateparks.ui.statePark

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
import com.example.utahstateparks.data.AppDatabase
import com.example.utahstateparks.databinding.StateParkFragmentBinding
import com.example.utahstateparks.ui.home.HomeFragmentDirections
import com.example.utahstateparks.ui.parkSelector.ParkSelectorFragmentDirections
import com.example.utahstateparks.ui.parkSelector.ParkSelectorViewModel

class StateParkFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: StateParkFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.state_park_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = StateParkFragmentArgs.fromBundle(requireArguments())

        val dataSource = AppDatabase.getInstance(application).stateParkDao()
        val viewModelFactory = StateParkViewModelFactory(arguments.stateParkKey, dataSource)

        val stateParkViewModel = ViewModelProvider(this, viewModelFactory).get(StateParkViewModel::class.java)

        binding.stateParkViewModel = stateParkViewModel
        binding.lifecycleOwner = this

        stateParkViewModel.navigateToMap.observe(viewLifecycleOwner, Observer { park ->
            park?.let {
                this.findNavController().navigate(
                    StateParkFragmentDirections.actionStateParkFragmentToMapFragment(park))
                stateParkViewModel.onNavigated()
            }
        })

        return binding.root
    }
}