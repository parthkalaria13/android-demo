package com.moondusk.mygitapp

interface RecyclerItemClickListener<T> {
    fun onItemClicked(item: T, pos: Int)
}