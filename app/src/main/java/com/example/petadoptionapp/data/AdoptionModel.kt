package com.example.petadoptionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date
import kotlin.random.Random

@Entity
data class AdoptionModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val _id: String = "N/A",

    @SerializedName("petname")
    var petName: String,

    @SerializedName("pettype")
    val petType: String,

    @SerializedName("petbreed")
    val petBreed: String,

    @SerializedName("ageyear")
    var ageYear: Int,

    @SerializedName("chipped")
    val chipped: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("datelisted")
    val dateListed: Date = Date(),

    @SerializedName("ownername")
    var ownerName: String,

    @SerializedName("ownercontact")
    var ownerContact: String,
)

val fakeAdoptions = List(30) { i ->
    AdoptionModel(
        id = 12345 + i,
        petName = "Pet $i",
        petType = if (i % 2 == 0) "Dog" else "Cat",
        petBreed = if (i % 2 == 0) "German Shepard" else "Tabby",
        ageYear = Random.nextInt(0, 15),
        chipped = if (i % 2 == 0) "Yes" else "No",
        location = "City $i",
        dateListed = Date(),
        ownerName = "Owner $i",
        ownerContact = "1234567890"
    )
}
