package com.hackaton.data.repository

import com.hackaton.data.api.ApiClient
import com.hackaton.data.boundaries.PoliticiansRepository
import com.hackaton.data.mapper.ApiObjectToApiPoliticians
import com.hackaton.data.mapper.ApiPoliticToPolitic
import com.hackaton.domain.entities.Politic
import io.reactivex.Single

class DefaultPoliticiansRepository(private val apiClient: ApiClient) : PoliticiansRepository {
    override fun getPoliticians(): Single<List<Politic>> {
        return apiClient.getCandidacies().map {
            ApiPoliticToPolitic.mapCollection(it.objects)
        }
    }
}