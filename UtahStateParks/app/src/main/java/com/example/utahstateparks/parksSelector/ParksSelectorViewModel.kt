package com.example.utahstateparks.parksSelector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utahstateparks.database.ParksDatabaseDao

class ParksSelectorViewModel(val database: ParksDatabaseDao) : ViewModel() {
    val parks = database.getAllParks()
}