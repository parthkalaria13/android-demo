package com.moondusk.mvvmkotlin.ui.agent

import androidx.lifecycle.viewModelScope
import com.moondusk.mvvmkotlin.data.DataResource
import com.moondusk.mvvmkotlin.error.ErrorCode.Companion.NETWORK_ERROR
import com.moondusk.mvvmkotlin.error.ErrorData
import com.moondusk.mvvmkotlin.models.agent.AgentsResponse
import com.moondusk.mvvmkotlin.repositories.AgentRepository
import com.moondusk.mvvmkotlin.ui._base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(
    private val repository: AgentRepository
) : BaseViewModel() {


    private val _agentData =
        MutableStateFlow<DataResource<AgentsResponse>>(DataResource.Initiated())
    val agentData: StateFlow<DataResource<AgentsResponse>> = _agentData.asStateFlow()

    private val _error = MutableSharedFlow<ErrorData>()
    val error: SharedFlow<ErrorData> = _error.asSharedFlow()

    init {
        getAgents()
    }

    fun getAgents() {
        viewModelScope.launch {
            _agentData.value = DataResource.Loading()
            delay(1000)
            repository.getAgents()
                .catch {
                    _agentData.value = DataResource.Failure(NETWORK_ERROR)
                    _error.emit(ErrorData(NETWORK_ERROR))
                }
                .collect {
                    _agentData.value = it
                    if (it is DataResource.Failure) {
                        _error.emit(ErrorData(it.errorCode, it.errorMessage))
                    }
                }
        }
    }
}