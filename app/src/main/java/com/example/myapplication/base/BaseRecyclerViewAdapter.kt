package com.example.myapplication.base

interface BaseRecyclerViewAdapter<T> {

    fun setData(list: List<T>)

    fun setItem(item: T)

    fun removeItem(position: Int)

}
