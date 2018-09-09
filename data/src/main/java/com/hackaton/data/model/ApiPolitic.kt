package com.hackaton.data.model

import com.google.gson.annotations.SerializedName

data class ApiPolitic(
        @SerializedName("name") val name: String,
        @SerializedName("picture") val picutre: String,
        @SerializedName("political_parties") val politicalParties: String,
        @SerializedName("cpf") val cpf: String,
        @SerializedName("website") val website: String
)