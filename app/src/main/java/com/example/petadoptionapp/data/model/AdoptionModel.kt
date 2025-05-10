package com.example.petadoptionapp.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID
import kotlin.random.Random

@Entity
data class AdoptionModel(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @Ignore
    @DocumentId val _id: String = "N/A",

    @SerializedName("petname")
    var petName: String = "Pet Name",
    @SerializedName("pettype")
    var petType: String = "Dog",
    @SerializedName("petbreed")
    var petBreed: String = "German Shepherd",
    @SerializedName("ageyear")
    var ageYear: Int = 0,
    @SerializedName("chipped")
    var chipped: String = "Yes",
    @SerializedName("location")
    var location: String = "Location",
    @SerializedName("datelisted")
    var dateListed: Date = Date(),
    @SerializedName("datemodified")
    var dateModified: Date = Date(),
    @SerializedName("ownername")
    var ownerName: String = "Owner Name",
    @SerializedName("ownercontact")
    var ownerContact: String = "087 777 7777",
    var email: String = "joe@bloggs.com"


)
