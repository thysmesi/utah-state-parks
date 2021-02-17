package com.example.utahstateparks.ui.parkSelector

import android.app.Application
import androidx.lifecycle.*
import com.example.utahstateparks.MainActivity
import com.example.utahstateparks.data.StatePark
import com.example.utahstateparks.data.StateParkDao
import kotlinx.coroutines.launch

class ParkSelectorViewModel(
        val database: StateParkDao,
        application: Application
    ) : AndroidViewModel(application) {

    val parks = database.getParks()

    private val _navigateToPark = MutableLiveData<Long?>()
    val navigateToPark
        get() = _navigateToPark

    fun onFavoriteClicked(park: StatePark) {
        park.favorite = !park.favorite
        viewModelScope.launch {
            database.update(park)


        }
    }
    fun onParkClicked(id: Long) {
        _navigateToPark.value = id
    }
    fun onParkNavigated() {
        _navigateToPark.value = null
    }
}