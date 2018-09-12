package com.hackaton.data.api

import com.hackaton.data.model.ApiObjects
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response

class ApiClient(private val dataSource: FuckyouDataSource) {

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