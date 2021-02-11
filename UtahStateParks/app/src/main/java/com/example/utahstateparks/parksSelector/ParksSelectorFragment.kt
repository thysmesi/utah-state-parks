package com.example.utahstateparks.parksSelector

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.utahstateparks.R
import com.example.utahstateparks.database.ParksDatabase
import com.example.utahstateparks.database.ParksDatabaseDao
import com.example.utahstateparks.databinding.ParksSelectorFragmentBinding
import com.example.utahstateparks.homeScreen.HomeScreenViewModel

class ParksSelectorFragment : Fragment() {

    private lateinit var viewModel: ParksSelectorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = ParksDatabase.getInstance(application).parksDatabaseDao
        val viewModelFactory = ParksSelectorViewModelFactory(dataSource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ParksSelectorViewModel::class.java)

        val binding: ParksSelectorFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.parks_selector_fragment, container, false)

        val adapter = StateParkAdapter()

        binding.parkList.adapter = adapter

        viewModel.parks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}