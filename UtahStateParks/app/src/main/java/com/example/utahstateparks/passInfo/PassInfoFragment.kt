package com.example.utahstateparks.passInfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.utahstateparks.R

class PassInfoFragment : Fragment() {

    companion object {
        fun newInstance() = PassInfoFragment()
    }

    private lateinit var viewModel: PassInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.park_passes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PassInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}