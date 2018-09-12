package com.hackaton.data.api

import com.hackaton.data.model.ApiObjects
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface FuckyouDataSource {

    @GET("/api/v0/politicians/")
    fun getCandidacies(): Single<Response<ApiObjects>>
}