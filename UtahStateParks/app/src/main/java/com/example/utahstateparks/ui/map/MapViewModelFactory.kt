package com.example.utahstateparks.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utahstateparks.data.StateParkDao
import com.example.utahstateparks.ui.statePark.StateParkViewModel

class MapViewModelFactory(
    private val stateParkKey: Long,
    private val dataSource: StateParkDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(stateParkKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}