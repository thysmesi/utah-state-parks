package com.example.utahstateparks.passInfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.utahstateparks.R
import com.example.utahstateparks.databinding.PassInfoFragmentBinding
import com.example.utahstateparks.passInfo.PassInfoFragmentDirections

class PassInfoFragment : Fragment() {

    private lateinit var viewModel: PassInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(PassInfoViewModel::class.java)

        val binding: PassInfoFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.pass_info_fragment, container, false)

        binding.passInfoViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().navigate(PassInfoFragmentDirections.actionPassInfoFragmentToHomeScreenFragment())

                viewModel.doneNavigating()
            }
        })
        viewModel.navigateToMap.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().navigate(PassInfoFragmentDirections.actionPassInfoFragmentToHomeMapFragment())

                viewModel.doneNavigating()
            }
        })

        return inflater.inflate(R.layout.pass_info_fragment, container, false)
    }
}