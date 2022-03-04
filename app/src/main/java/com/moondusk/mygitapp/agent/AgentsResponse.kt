package com.moondusk.mvvmkotlin.models.agent

data class AgentsResponse(
    val data: List<Data>,
    val message: String,
    val pitems: Int,
    val psize: Int,
    val status: Boolean
)