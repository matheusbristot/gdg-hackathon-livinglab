package com.hackaton.data.boundaries

import com.hackaton.domain.entities.Post
import io.reactivex.Single

interface PostRepository {
    fun getPosts(id: Int): Single<Post>
}