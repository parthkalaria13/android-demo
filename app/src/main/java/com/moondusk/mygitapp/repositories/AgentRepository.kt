package com.moondusk.mvvmkotlin.repositories

import com.moondusk.mvvmkotlin.constant.BASIC_C
import com.moondusk.mvvmkotlin.data.DataResource
import com.moondusk.mvvmkotlin.error.ErrorCode.Companion.DATA_ERROR
import com.moondusk.mvvmkotlin.error.ErrorCode.Companion.NO_INTERNET_CONNECTION
import com.moondusk.mvvmkotlin.models.agent.AgentsRequest
import com.moondusk.mvvmkotlin.models.agent.AgentsResponse
import com.moondusk.mvvmkotlin.network.ApiInterface
import com.moondusk.mvvmkotlin.utilities.NetworkConnectivityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AgentRepository @Inject constructor(
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val apiInterface: ApiInterface
) {

    suspend fun getAgents(): Flow<DataResource<AgentsResponse>> {
        return flow {
            if (!networkConnectivityManager.isInternetAvailable()) {
                emit(DataResource.Failure(NO_INTERNET_CONNECTION))
            } else {
                val agentsRequest = AgentsRequest("device_id", BASIC_C, "1")
                val result: AgentsResponse = apiInterface.getAgents(agentsRequest)
                if (result.status && !result.data.isNullOrEmpty()) {
                    emit(DataResource.Success(result))
                } else {
                    emit(DataResource.Failure(DATA_ERROR, result.message))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}