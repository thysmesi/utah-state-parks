package com.example.utahstateparks.ui.parkSelector

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utahstateparks.data.StateParkDao

class ParkSelectorViewModelFactory(
    private val dataSource: StateParkDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParkSelectorViewModel::class.java)) {
            return ParkSelectorViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}