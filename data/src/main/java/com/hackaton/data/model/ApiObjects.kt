package com.hackaton.data.model

import com.google.gson.annotations.SerializedName

data class ApiObjects(
        @SerializedName("objects") val objects: List<ApiPolitic>
)