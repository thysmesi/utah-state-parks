package com.example.utahstateparks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "stateparks")
data class StatePark(
    @PrimaryKey(autoGenerate = true)
    var parkId: Long = 0L,
    val parkName: String,
    val county: String,
    val size: String,
    val elevation: String, // how often the plant should be watered, in days
    val yearEstablished: Int,
    val yearlyVisitors: String,
    val remarks: String,
    var favorite: Boolean = false

    )
