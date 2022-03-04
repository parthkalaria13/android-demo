package com.moondusk.mygitapp

sealed class DataResource<T>() {

    data class Success<T>(val data: T) : DataResource<T>()
    data class Failure<T>(val errorCode: Int, val errorMessage: String = "") : DataResource<T>()
    data class Loading<T>(val nothing: Nothing? = null) : DataResource<T>()
    data class Initiated<T>(val nothing: Nothing? = null) : DataResource<T>()
}