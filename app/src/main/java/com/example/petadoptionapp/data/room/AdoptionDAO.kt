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

    @Query("UPDATE adoptionModel SET petName=:petName WHERE id = :id")
    suspend fun update(id: Int, petName:String)

    @Delete
    suspend fun delete(adoption: AdoptionModel)

    @Query("SELECT * FROM adoptionmodel WHERE id=:id")
    fun get(id: Int): Flow<AdoptionModel>

}
