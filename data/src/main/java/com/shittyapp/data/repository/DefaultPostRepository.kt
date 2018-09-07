package com.shittyapp.data.repository

import com.shittyapp.data.api.ApiClient
import com.shittyapp.data.boundaries.PostRepository
import com.shittyapp.data.mapper.ApiPostToPostModel
import com.shittyapp.domain.entities.Post
import io.reactivex.Single

class DefaultPostRepository(val client: ApiClient): PostRepository {
    override fun getPosts(id: Int) = client.getPosts(id).map(ApiPostToPostModel::transform)
}