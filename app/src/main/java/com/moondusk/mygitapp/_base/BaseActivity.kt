package com.moondusk.mvvmkotlin.ui._base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> :
    AppCompatActivity() {

    protected lateinit var _binding: VB

    protected abstract fun initViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = initViewBinding(layoutInflater)
        setContentView(_binding.root)
        initViews()
        observersAndEvents()
    }

    protected abstract fun initViews()
    protected abstract fun observersAndEvents()
}