package com.moondusk.mvvmkotlin.models.agent

data class AgentsRequest(
    val device_id: String,
    val device_code: String,
    val page: String
)