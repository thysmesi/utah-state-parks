package com.example.utahstateparks.ui.statePark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utahstateparks.data.StatePark
import com.example.utahstateparks.data.StateParkDao

class StateParkViewModel(
    private val stateParkKey: Long = 0L,
    dataSource: StateParkDao) : ViewModel() {

    val database = dataSource

    private val park = MediatorLiveData<StatePark>()

    fun getPark() = park

    private val _navigateToMap = MutableLiveData<Long?>()
    val navigateToMap
        get() = _navigateToMap

    fun onMapButtonClicked() {
        _navigateToMap.value = park.value?.parkId
    }
    fun onNavigated() {
        _navigateToMap.value = null
    }

    init {
        park.addSource(database.getParkWithId(stateParkKey), park::setValue)
    }
}