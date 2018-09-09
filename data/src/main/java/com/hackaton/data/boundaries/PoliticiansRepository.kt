package com.hackaton.data.boundaries

import com.hackaton.domain.entities.Objects
import com.hackaton.domain.entities.Politic
import io.reactivex.Single

interface PoliticiansRepository {
    fun getPoliticians(): Single<Objects>
}