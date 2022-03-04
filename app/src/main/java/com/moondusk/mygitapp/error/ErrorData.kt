package com.moondusk.mvvmkotlin.error

data class ErrorData(
    val errorCode: Int,
    val errorMessage: String = ""
)