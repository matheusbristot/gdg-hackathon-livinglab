package com.bodybysimone.presentation.core.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


class NoSwipeViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    override fun performClick(): Boolean {
        return false
    }

    fun goToNext() {
        adapter?.let {
            if (currentItem != it.count - 1) {
                setCurrentItem(currentItem + 1, true)
            }
        }
    }

    fun goToPrevious() {
        if (currentItem != 0) {
            setCurrentItem(currentItem - 1, true)
        }
    }
}