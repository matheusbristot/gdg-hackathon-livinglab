package com.shittyapp.data.mapper

import com.shittyapp.data.models.ApiPost
import com.shittyapp.domain.entities.Post

object ApiPostToPostModel: Mapper<ApiPost, Post>() {
    override fun transform(i: ApiPost) = Post(
            userId =  i.userId,
            id = i.id,
            title = i.title,
            body = i.body
    )
}