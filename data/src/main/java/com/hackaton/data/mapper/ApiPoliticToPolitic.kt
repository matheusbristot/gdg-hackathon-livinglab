package com.hackaton.data.mapper

import com.hackaton.data.model.ApiObjects
import com.hackaton.data.model.ApiPolitic
import com.hackaton.domain.entities.Objects
import com.hackaton.domain.entities.Politic

object ApiPoliticToPolitic : Mapper<ApiObjects, Objects>() {
    override fun transform(i: ApiObjects)= Objects(i.objects.map(::mapper))

    private fun mapper(apiPolitic: ApiPolitic) = Politic (
            name = apiPolitic.name,
            picutre = apiPolitic.picutre,
            politicalParties = apiPolitic.politicalParties,
            website = apiPolitic.website,
            cpf = apiPolitic.cpf
    )

}