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
    @DocumentId val _id: String = "N/A",
    var petName: String = "Pet Name",
    var petType: String = "Dog",
    var petBreed: String = "German Shepherd",
    var ageYear: Int = 0,
    var chipped: String = "Yes",
    var location: String = "Location",
    var dateListed: Date = Date(),
    var dateModified: Date = Date(),
    var ownerName: String = "Owner Name",
    var ownerContact: String = "087 777 7777",
    var email: String = "joe@bloggs.com"
)
