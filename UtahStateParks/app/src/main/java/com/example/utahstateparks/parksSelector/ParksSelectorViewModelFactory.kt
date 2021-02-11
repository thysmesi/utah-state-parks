package com.example.utahstateparks.parksSelector

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utahstateparks.database.ParksDatabaseDao

class ParksSelectorViewModelFactory(
    private val dataSource: ParksDatabaseDao,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParksSelectorViewModel::class.java)) {
            return ParksSelectorViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

