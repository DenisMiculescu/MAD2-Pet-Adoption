package com.example.petadoptionapp.data.repository

import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.data.room.AdoptionDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject
constructor(private val adoptionDAO: AdoptionDAO) {
    fun getAll(): Flow<List<AdoptionModel>>
            = adoptionDAO.getAll()

    suspend fun insert(adoption: AdoptionModel)
    { adoptionDAO.insert(adoption) }

    suspend fun update(adoption: AdoptionModel)
    { adoptionDAO.update(adoption.id, adoption.petName) }


    suspend fun delete(adoption: AdoptionModel)
    { adoptionDAO.delete(adoption) }

    fun get(id: String) = adoptionDAO.get(id)


}
