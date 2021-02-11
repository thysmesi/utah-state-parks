package com.example.utahstateparks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StatePark::class], version = 1, exportSchema = false)
abstract class ParksDatabase : RoomDatabase() {

    abstract val parksDatabaseDao: ParksDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ParksDatabase? = null

        fun getInstance(context: Context): ParksDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ParksDatabase::class.java,
                        "state_parks_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}