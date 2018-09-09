package com.hackaton.notice.util.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import java.io.Serializable

object Arguments {
    const val ARG_USER: String = "ARG_USER"
    const val ARG_RESULT: String = "ARG_RESULT"
}

/*
* Start an Activity for given class T and allow to work on intent with "run" lambda function
*/
fun Fragment.withArguments(vararg arguments: Pair<String, Serializable>): Fragment {
    val bundle = Bundle()
    arguments.forEach { bundle.putSerializable(it.first, it.second) }
    this.arguments = bundle
    return this
}

/**
 * Retrieve property from intent
 */
fun <T : Any> FragmentActivity.argument(key: String) = lazy { intent.extras[key] as T }

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> FragmentActivity.argument(key: String, defaultValue: T? = null) = lazy {
    intent?.extras?.let { it[key] as? T } ?: defaultValue
}

/**
 * Retrieve property from intent
 */
fun <T : Any> Fragment.argument(key: String) = lazy { arguments?.get(key) as T }

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> Fragment.argument(key: String, defaultValue: T? = null) = lazy {
    arguments?.get(key)  as? T ?: defaultValue
}