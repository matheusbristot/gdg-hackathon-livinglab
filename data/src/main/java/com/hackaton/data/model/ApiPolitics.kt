package com.hackaton.data.model

import com.google.gson.annotations.SerializedName

data class ApiPolitics(
        @SerializedName("politicians")
        val politicians: List<ApiPolitic>
)