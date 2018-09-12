package com.hackaton.domain.interactors

import com.hackaton.domain.*
import io.reactivex.Single

class CalculateQuiz {

    fun execute(list: List<Int>): Single<String> {
        val pf = resolvePF(list)
        val pe = resolvePE(list)
        return Single.just(when {
            pf <= 70 && pe <= 70 -> TOTALITARIO
            pf >= 70 && pe >= 70 -> LIBERTARIO
            pf >= 50 && pe >= 50 -> DIREITA
            pf >= 50 && pe <= 50 -> ESQUERDA
            else -> CENTRISTA
        /*

        pf <= 50 && pe <= 50 -> SOCIALISTA
        (pf in 51..100) && (pe in 0..30) -> ESQUERDA
        (pf in 70..100) && (pe in 50..100) -> LIBERTARIO
        (pf in 0..30) && (pe in 50..100) -> DIREITA*/
        })
    }

    private fun resolvePF(list: List<Int>): Int {
        var freedom = 0
        list.forEachIndexed { index, i ->
            when {
                index <= 4 -> freedom += i
            }
        }
        return freedom
    }

    private fun resolvePE(list: List<Int>): Int {
        var economic = 0
        list.forEachIndexed { index, i ->
            when (index) {
                in 5..9 -> economic += i
            }
        }
        return economic
    }
}