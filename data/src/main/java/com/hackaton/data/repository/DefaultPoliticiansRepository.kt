package com.hackaton.data.repository

import com.hackaton.data.api.ApiClient
import com.hackaton.data.boundaries.PoliticiansRepository
import com.hackaton.data.mapper.ApiPoliticToPolitic

class DefaultPoliticiansRepository(private val apiClient: ApiClient) : PoliticiansRepository {
    override fun getPoliticians() = apiClient.getCandidacies().map(ApiPoliticToPolitic::transform)
}