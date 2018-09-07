package com.hackaton.data.mapper

import com.hackaton.data.models.ApiPost
import com.hackaton.domain.entities.Post

object ApiPostToPostModel: Mapper<ApiPost, Post>() {
    override fun transform(i: ApiPost) = Post(
            userId =  i.userId,
            id = i.id,
            title = i.title,
            body = i.body
    )
}