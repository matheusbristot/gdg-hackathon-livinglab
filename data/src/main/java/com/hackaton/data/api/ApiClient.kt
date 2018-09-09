package com.hackaton.data.api

import com.hackaton.data.model.ApiObjects
import com.hackaton.data.model.ApiPolitic
import com.hackaton.data.models.ApiPost
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response

class ApiClient(private val dataSource: FuckyouDataSource) {
    fun getPosts(id: Int): Single<ApiPost> {
        return makeRequest(dataSource.getId(id))
    }

    fun getCandidacies(): Single<ApiObjects> {
        return makeRequest(dataSource.getCandidacies())
    }

    private fun <T> unwrap(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { upstream ->
            upstream.map(Response<T>::body)
        }
    }

    private fun <T> makeRequest(request: Single<Response<T>>): Single<T> {
        return request.compose(unwrap())
    }
}