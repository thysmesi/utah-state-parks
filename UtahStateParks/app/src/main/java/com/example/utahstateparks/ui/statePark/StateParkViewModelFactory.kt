package com.example.utahstateparks.ui.statePark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utahstateparks.data.StateParkDao
import com.example.utahstateparks.ui.parkSelector.ParkSelectorViewModel

class StateParkViewModelFactory(
    private val stateParkKey: Long,
    private val dataSource: StateParkDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StateParkViewModel::class.java)) {
            return StateParkViewModel(stateParkKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}