package com.hackaton.data.repository

import com.hackaton.data.api.ApiClient
import com.hackaton.data.boundaries.PostRepository
import com.hackaton.data.mapper.ApiPostToPostModel

class DefaultPostRepository(val client: ApiClient): PostRepository {
    override fun getPosts(id: Int) = client.getPosts(id).map(ApiPostToPostModel::transform)
}