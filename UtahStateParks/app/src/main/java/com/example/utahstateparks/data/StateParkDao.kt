package com.example.utahstateparks.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StateParkDao {
    @Query("SELECT * FROM stateparks")
    fun getParks(): LiveData<List<StatePark>>

    @Query("SELECT * FROM stateparks WHERE favorite == 1")
    fun getFavorites(): LiveData<List<StatePark>>

    @Query("SELECT * from stateparks WHERE parkId = :key")
    fun getParkWithId(key: Long): LiveData<StatePark>

    @Update
    suspend fun update(park: StatePark)

    @Insert()
    suspend fun  insertOne(statePark: StatePark)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(statePark: List<StatePark>)
}
