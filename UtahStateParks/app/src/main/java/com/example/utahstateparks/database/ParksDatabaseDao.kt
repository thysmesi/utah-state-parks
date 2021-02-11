package com.example.utahstateparks.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ParksDatabaseDao {

    @Insert
    suspend fun insert(park: StatePark)

    @Update
    suspend fun update(park: StatePark)

    @Query("SELECT * FROM state_park_table WHERE parkId = :key")
    suspend fun get(key: Long): StatePark?

    @Query("SELECT * FROM state_park_table")
    fun getAllParks(): LiveData<List<StatePark>>

    @Query("DELETE FROM state_park_table")
    suspend fun clear()

    @Query("SELECT * FROM state_park_table WHERE favorite = 'True' ")
    suspend fun favorites()
}