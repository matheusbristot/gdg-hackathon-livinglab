package com.hackaton.notice.util.rx

import com.hackaton.domain.di.SchedulerProvider
import io.reactivex.Single

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())