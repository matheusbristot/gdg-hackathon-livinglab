package com.hackaton.data.mapper

import com.hackaton.data.model.ApiObjects
import com.hackaton.data.model.ApiPolitics

object ApiObjectToApiPoliticians: Mapper<ApiObjects, ApiPolitics>() {
    override fun transform(i: ApiObjects) = ApiPolitics(
        politicians = i.objects
    )
}