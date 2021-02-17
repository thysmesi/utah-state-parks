package com.example.utahstateparks.ui.boatingInfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.utahstateparks.R

class BoatingInfoFragment : Fragment() {

    companion object {
        fun newInstance() = BoatingInfoFragment()
    }

    private lateinit var viewModel: BoatingInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.boating_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BoatingInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}