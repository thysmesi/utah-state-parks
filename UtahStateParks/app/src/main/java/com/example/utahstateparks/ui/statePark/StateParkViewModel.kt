package com.example.utahstateparks.ui.statePark

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.utahstateparks.data.StatePark
import com.example.utahstateparks.data.StateParkDao

class StateParkViewModel(
    private val stateParkKey: Long = 0L,
    dataSource: StateParkDao) : ViewModel() {

    val database = dataSource

    private val park = MediatorLiveData<StatePark>()

    fun getPark() = park

    init {
        park.addSource(database.getParkWithId(stateParkKey), park::setValue)
    }
}