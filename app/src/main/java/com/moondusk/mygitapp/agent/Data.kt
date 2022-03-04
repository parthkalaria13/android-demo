package com.moondusk.mvvmkotlin.models.agent

data class Data(
    val desc: String,
    val gender: String,
    val icon: String,
    val id: String,
    val name: String,
    val origin: Origin,
    val role: Role
)