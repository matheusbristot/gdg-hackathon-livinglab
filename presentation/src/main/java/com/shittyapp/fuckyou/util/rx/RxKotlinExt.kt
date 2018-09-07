package com.shittyapp.fuckyou.util.rx

import com.shittyapp.domain.di.SchedulerProvider
import io.reactivex.Single

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())