package com.hackaton.data.mapper

import com.hackaton.data.model.ApiPolitic
import com.hackaton.domain.entities.Politic

object ApiPoliticToPolitic: Mapper<ApiPolitic, Politic>() {
    override fun transform(i: ApiPolitic) = Politic(
        i.id, i.name, i.picutre, i.cpf, i.website
    )
}