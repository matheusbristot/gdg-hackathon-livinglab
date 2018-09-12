package com.hackaton.notice.util.view

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


// ImageView

fun ImageView.load(url: Any?, @DrawableRes placeholderRes: Int?, centerCrop: Boolean? = null) {
    load(url, placeholderRes, null, centerCrop)
}

fun ImageView.load(url: Any?, rounded: Boolean?, @DrawableRes placeholderRes: Int? = null) {
    load(url, placeholderRes, rounded, null)
}

fun ImageView.load(url: Any?, @DrawableRes placeholderRes: Int?, rounded: Boolean?, centerCrop: Boolean?) {
    val placeholder = if (placeholderRes == null) null else ContextCompat.getDrawable(context, placeholderRes)
    load(url, placeholder, rounded, centerCrop)
}

fun ImageView.load(url: Any?, placeholderDrawable: Drawable?, rounded: Boolean?, centerCrop: Boolean?) {
    val typeUrlContent = when (url) {
        is Int -> url
        is String -> url
        else -> {
            if (url == null) null
            else throw RuntimeException("Don't resolve type to: ${url.javaClass}")
        }
    }
    val requestOptions = RequestOptions()
    if (placeholderDrawable != null) {
        requestOptions.placeholder(placeholderDrawable).error(placeholderDrawable)
    }
    when {
        rounded == true -> requestOptions.circleCrop()
        centerCrop == true -> requestOptions.centerCrop()
        else -> requestOptions.centerInside()
    }
    Glide.with(context).load(typeUrlContent).apply(requestOptions).into(this).waitForLayout().clearOnDetach()
}
