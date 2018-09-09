package com.hackaton.data.model

import com.google.gson.annotations.SerializedName

data class ApiPolitic(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("picture") val picutre: String?,
        @SerializedName("cpf") val cpf: String?,
        @SerializedName("website") val website: String?
)