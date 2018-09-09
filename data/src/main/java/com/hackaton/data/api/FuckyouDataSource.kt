package com.hackaton.data.api

import com.hackaton.data.model.ApiObjects
import com.hackaton.data.models.ApiPost
import com.hackaton.data.model.ApiPolitic
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FuckyouDataSource {

    @GET("posts/{id}")
    @Headers("Content-type: application/json")
    fun getId(@Path("id") id: Int): Single<Response<ApiPost>>

    @GET("posts")
    @Headers("Content-type: application/json")
    fun getPosts(): Single<Response<List<ApiPost>>>

    @GET("/api/v0/candidacies/")
    fun getCandidacies(): Single<Response<ApiObjects>>

}