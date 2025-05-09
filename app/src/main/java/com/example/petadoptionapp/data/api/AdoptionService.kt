package com.example.petadoptionapp.data.api

import com.example.petadoptionapp.data.model.AdoptionModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AdoptionService {
    @GET(ServiceEndPoints.ADOPTIONS_ENDPOINT)
    suspend fun getall(): Response<List<AdoptionModel>>

    @GET(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{id}")
    suspend fun get(@Path("id") id: String): Response<List<AdoptionModel>>

    @DELETE(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{id}")
    suspend fun delete(@Path("id") id: String): AdoptionWrapper

    @POST(ServiceEndPoints.ADOPTIONS_ENDPOINT)
    suspend fun post(@Body adoption: AdoptionModel): AdoptionWrapper

    @PUT(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{id}")
    suspend fun put(@Path("id") id: String,
                    @Body adoption: AdoptionModel
    ): AdoptionWrapper
}
