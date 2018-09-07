package com.shittyapp.data.boundaries

import com.shittyapp.domain.entities.Post
import io.reactivex.Single

interface PostRepository {
    fun getPosts(id: Int): Single<Post>
}