package com.example.petadoptionapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.petadoptionapp.data.AdoptionModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AdoptionDAO {
    @Query("SELECT * FROM adoptionmodel")
    fun getAll(): Flow<List<AdoptionModel>>

    @Insert
    suspend fun insert(adoption: AdoptionModel)

    @Update
    suspend fun update(adoption: AdoptionModel)

    @Delete
    suspend fun delete(adoption: AdoptionModel)
}
