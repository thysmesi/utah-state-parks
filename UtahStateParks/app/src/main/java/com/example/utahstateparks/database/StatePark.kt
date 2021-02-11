package com.example.utahstateparks.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state_park_table")
data class StatePark(
    @PrimaryKey(autoGenerate = true)
    var parkId: Long = 0L,

    @ColumnInfo(name = "park_name")
    val parkName: String,

    @ColumnInfo(name = "favorite")
    val favorite: Boolean = false
)
