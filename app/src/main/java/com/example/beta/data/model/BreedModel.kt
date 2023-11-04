package com.example.beta.data.model

import com.google.gson.annotations.SerializedName

data class BreedModel(
    @SerializedName("breed") val breed: String,
    @SerializedName("subBreeds") val subBreeds: List<String>?
)