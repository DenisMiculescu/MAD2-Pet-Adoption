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

    @GET(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{email}")
    suspend fun getall(
        @Path("email") email: String)
            : Response<List<AdoptionModel>>

    @GET(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun get(@Path("email") email: String,
                    @Path("id") id: String): Response<AdoptionModel>

    @DELETE(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun delete(@Path("email") email: String,
                       @Path("id") id: String): Response<Void>

    @POST(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{email}")
    suspend fun post(@Path("email") email: String,
                     @Body adoption: AdoptionModel): AdoptionWrapper

    @PUT(ServiceEndPoints.ADOPTIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun put(@Path("email") email: String,
                    @Path("id") id: String,
                    @Body adoption: AdoptionModel
    ): AdoptionWrapper
}
