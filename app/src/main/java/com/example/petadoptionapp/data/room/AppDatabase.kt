package com.example.petadoptionapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.petadoptionapp.data.model.AdoptionModel

@Database(entities = [AdoptionModel::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAdoptionDAO(): AdoptionDAO
}
