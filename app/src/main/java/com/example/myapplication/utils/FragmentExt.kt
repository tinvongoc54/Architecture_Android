package com.example.myapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun Fragment.goTo(fragment: Fragment, frameId: Int, addToBackStack: Boolean = true) =
    childFragmentManager.transact {
        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        replace(frameId, fragment)
    }

fun Fragment.goBack() = childFragmentManager.popBackStackImmediate()

fun Fragment.addTo(fragment: Fragment, frameid: Int) =
    childFragmentManager.transact {
        add(frameid, fragment)
    }

inline fun <reified T: Any> Fragment.extra(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T: Any> Fragment.extraNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) =
    beginTransaction().apply { action() }.commit()