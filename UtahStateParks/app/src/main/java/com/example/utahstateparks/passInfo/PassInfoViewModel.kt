package com.example.utahstateparks.passInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PassInfoViewModel : ViewModel() {
    private val _navigateToHomeScreen = MutableLiveData<Boolean?>()
    val navigateToHomeScreen: LiveData<Boolean?>
        get() = _navigateToHomeScreen

    private val _navigateToMap = MutableLiveData<Boolean?>()
    val navigateToMap: LiveData<Boolean?>
        get() = _navigateToMap

    fun doneNavigating() {
        _navigateToHomeScreen.value = null
        _navigateToMap.value = null
    }

    fun onClickHomeButton() {
        Log.i("asdf", "home")
        _navigateToHomeScreen.value = true
    }
    fun onClickMapButton() {
        Log.i("asdf", "map")
        _navigateToMap.value = true
    }
}