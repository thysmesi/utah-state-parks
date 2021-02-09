package com.example.utahstateparks.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _navigateToBoatingInfo = MutableLiveData<Boolean?>()
    val navigateToBoatingInfo: LiveData<Boolean?>
        get() = _navigateToBoatingInfo

    private val _navigateToPasses = MutableLiveData<Boolean?>()
    val navigateToPasses: LiveData<Boolean?>
        get() = _navigateToPasses

    private val _navigateToAtvInfo = MutableLiveData<Boolean?>()
    val navigateToAtvInfo: LiveData<Boolean?>
        get() = _navigateToAtvInfo

    private val _navigateToParks = MutableLiveData<Boolean?>()
    val navigateToParks: LiveData<Boolean?>
        get() = _navigateToParks

    private val _navigateToMap = MutableLiveData<Boolean?>()
    val navigateToMap: LiveData<Boolean?>
        get() = _navigateToMap


    fun doneNavigating() {
        _navigateToBoatingInfo.value = null
        _navigateToAtvInfo.value = null
        _navigateToPasses.value = null
        _navigateToParks.value = null
        _navigateToMap.value = null
    }

    fun onClickBoatButton() {
        _navigateToBoatingInfo.value = true
    }
    fun onClickPassButton() {
        _navigateToPasses.value = true
    }
    fun onClickAtvButton() {
        _navigateToAtvInfo.value = true
    }
    fun onClickParksButton() {
        _navigateToParks.value = true
    }
    fun onClickMapButton() {
        _navigateToMap.value = true
    }
}