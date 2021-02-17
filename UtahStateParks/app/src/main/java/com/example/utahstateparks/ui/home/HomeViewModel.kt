package com.example.utahstateparks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _navigateToParkSelector = MutableLiveData<Boolean?>()
    val navigateToParkSelector: LiveData<Boolean?>
        get() = _navigateToParkSelector

    private val _navigateToAtvInfo = MutableLiveData<Boolean?>()
    val navigateToAtvInfo: LiveData<Boolean?>
        get() = _navigateToAtvInfo

    private val _navigateToBoatingInfo = MutableLiveData<Boolean?>()
    val navigateToBoatingInfo: LiveData<Boolean?>
        get() = _navigateToBoatingInfo

    fun onClickParkSelector() {
        _navigateToParkSelector.value = true
    }
    fun onClickAtvInfo() {
        _navigateToAtvInfo.value = true
    }
    fun onClickBoatingInfo() {
        _navigateToBoatingInfo.value = true
    }

    fun doneNavigating() {
        _navigateToParkSelector.value = null
        _navigateToAtvInfo.value = null
        _navigateToBoatingInfo.value = null
    }
}