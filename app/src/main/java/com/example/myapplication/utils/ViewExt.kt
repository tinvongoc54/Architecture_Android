package com.example.myapplication.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

fun ViewGroup.inflate(layoutId: Int, attachtToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachtToRoot)